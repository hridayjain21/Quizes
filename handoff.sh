#!/usr/bin/env bash
set -euo pipefail

OUT="handoff_$(date +%Y%m%d_%H%M%S)"
mkdir -p "$OUT"

# 0) Top-level view
( command -v tree >/dev/null && tree -L 2 || find . -maxdepth 2 -type d ) > "$OUT/PROJECT_TREE.txt" 2>/dev/null || true

# 1) Copy any top-level build files
for f in pom.xml build.gradle settings.gradle gradle.properties docker-compose.yml; do
  [ -f "$f" ] && cp -f "$f" "$OUT/" || true
done

# 2) List your modules explicitly (based on your structure)
MODULES=( APIgateway configServer serviceReg QuestionService QuizService )

copy_module () {
  local MOD="$1"
  local DEST="$OUT/$MOD"
  mkdir -p "$DEST"

  # Build files
  cp -f "$MOD/pom.xml" "$DEST/" 2>/dev/null || true
  cp -f "$MOD/build.gradle" "$DEST/" 2>/dev/null || true

  # App configs
  for cf in application.yml application.yaml application.properties bootstrap.yml bootstrap.yaml bootstrap.properties; do
    [ -f "$MOD/src/main/resources/$cf" ] && cp -f "$MOD/src/main/resources/$cf" "$DEST/" || true
  done
  # Any profile-specific configs
  find "$MOD/src/main/resources" -maxdepth 1 -type f \( -name "application-*.yml" -o -name "application-*.yaml" -o -name "application-*.properties" \) -exec cp -f {} "$DEST/" \; 2>/dev/null || true

  # Main app classes (to see annotations)
  find "$MOD/src/main/java" -maxdepth 3 -name "*Application.java" -print -exec cp -f {} "$DEST/" \; 2>/dev/null || true

  # Gateway route config / Eureka / Feign / Actuator bits
  grep -RIl --include='*.yml' --include='*.yaml' --include='*.properties' \
    -e "spring.cloud.gateway" -e "eureka" -e "feign" -e "management.endpoints" -e "management.endpoint.prometheus" \
    "$MOD/src/main/resources" 2>/dev/null | xargs -I{} cp -f {} "$DEST/" 2>/dev/null || true

  # Feign interfaces, route locators, security configs (top 25 files)
  grep -RIl --include='*.java' -e "@FeignClient" -e "RouteLocator" -e "WebSecurityConfigurer" -e "SecurityFilterChain" \
    "$MOD/src/main/java" 2>/dev/null | head -n 25 | xargs -I{} cp -f {} "$DEST/" 2>/dev/null || true

  # Dockerfiles (if any)
  [ -f "$MOD/Dockerfile" ] && cp -f "$MOD/Dockerfile" "$DEST/" || true
}

for m in "${MODULES[@]}"; do
  [ -d "$m" ] && copy_module "$m" || echo "Skipping missing module: $m"
done

# 3) Create a small NOTES file for ports & run cmds
cat > "$OUT/NOTES.txt" <<'EOF'
# Please fill these quickly:
Java version:
Spring Boot version:
Spring Cloud version(s):

Local ports:
- Eureka (serviceReg): ______
- API Gateway (APIgateway): ______
- QuizService: ______
- QuestionService: ______
- configServer (if used): ______

Inter-service calls:
- Who calls whom (Feign/WebClient)? e.g., APIgateway -> QuizService, QuizService -> QuestionService, etc.

DB / Message broker (if any):
Build commands (e.g., mvn clean package):
Run commands (how you start each service locally):
EOF

zip -r "$OUT.zip" "$OUT" >/dev/null
echo "==> Created $OUT.zip — please upload that file here."

