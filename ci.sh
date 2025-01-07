echo "" >> gradle.properties
echo "org.gradle.parallel=false" >> gradle.properties
echo "kotlin.incremental=false" >> gradle.properties

# Build
if [ -z "$CIRCLE_PR_REPONAME" ]; then
  echo "signing.keyId=${SIGNING_KEY}" >> "gradle.properties"
  echo "signing.password=${SIGNING_PASSWORD}" >> "gradle.properties"
  echo "signing.secretKeyRingFile=../maven.keystore" >> "gradle.properties"
  gpg --cipher-algo AES256 --yes --batch --passphrase=$ENC_FILE_KEY maven.keystore.gpg
  ./gradlew build dokkaHtml publish --no-daemon
else
  ./gradlew build --no-daemon
fi

# Code Cov
bash <(curl -s https://codecov.io/bash)

# GH Pages
if [ -z "$CIRCLE_PR_REPONAME" ] && [ "master" = "$CIRCLE_BRANCH" ]; then
  git config --global user.email $GH_EMAIL
  git config --global user.name $GH_NAME
  cp -r .circleci common/gh-pages/.circleci
  cd common/gh-pages
  git init
  git checkout --orphan gh-pages
  git add -A
  git commit -m "Automated deployment of ${CIRCLE_BRANCH} ${CIRCLE_SHA1}" --allow-empty
  git push -q https://${GH_PERSONAL_TOKEN}@github.com/ToxicBakery/${CIRCLE_PROJECT_REPONAME}.git gh-pages --force
fi
