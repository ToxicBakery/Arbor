set -x
set -e

# Ensure a blank new line on gradle.properties
echo "" >> "gradle.properties"
echo "kotlin.incremental=false" >> "gradle.properties"
echo "org.gradle.console=plain" >> "gradle.properties"

# Build
if [ -z "${CIRCLE_PR_REPONAME}" ]; then
  echo "branch=${CIRCLE_BRANCH}" >> "gradle.properties"
  echo "ci=true" >> "gradle.properties"
  echo "mavenCentralUsername=${SONATYPE_USERNAME}" >> "gradle.properties"
  echo "mavenCentralPassword=${SONATYPE_PASSWORD}" >> "gradle.properties"
  echo "signing.keyId=${SIGNING_KEY}" >> "gradle.properties"
  echo "signing.password=${SIGNING_PASSWORD}" >> "gradle.properties"
  echo "signing.secretKeyRingFile=../maven.keystore" >> "gradle.properties"
  gpg --no-tty --cipher-algo AES256 --yes --batch --passphrase=$ENC_FILE_KEY --decrypt maven.keystore.gpg > maven.keystore
  ./gradlew kotlinUpgradePackageLock -PLibrariesOnly=true publish
else
  ./gradlew kotlinUpgradePackageLock -PLibrariesOnly=true build
fi

# GH Pages
if [ -z "${CIRCLE_PR_REPONAME}" ] && [ "master" = "${CIRCLE_BRANCH}" ]; then
  git config --global user.email $GH_EMAIL
  git config --global user.name $GH_NAME
  cp -r .circleci gh-pages/.circleci
  cd gh-pages
  git init
  git checkout --orphan gh-pages
  git add -A
  git commit -m "Automated deployment of ${CIRCLE_BRANCH} ${CIRCLE_SHA1}" --allow-empty
  git push -q https://${GH_PERSONAL_TOKEN}@github.com/ToxicBakery/${CIRCLE_PROJECT_REPONAME}.git gh-pages --force
fi
