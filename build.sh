#!/usr/bin/env bash
set -euo pipefail
: "${ANDROID_HOME:?Configure ANDROID_HOME para o SDK Android}"
BT="$ANDROID_HOME/build-tools/35.0.0"
PLATFORM="$ANDROID_HOME/platforms/android-35/android.jar"
mkdir -p build/classes build/dex
javac -source 8 -target 8 -encoding UTF-8 -classpath "$PLATFORM:lib/zxing.jar" -d build/classes src/br/com/estoquebip/*.java
mapfile -t CLASSES < <(find build/classes -name '*.class')
"$BT/d8" --lib "$PLATFORM" --min-api 26 --output build/dex "${CLASSES[@]}" lib/zxing.jar
"$BT/aapt" package -f -M AndroidManifest.xml -S res -A assets -I "$PLATFORM" -F build/unsigned.apk
(cd build/dex && zip -q ../unsigned.apk classes.dex)
"$BT/zipalign" -f 4 build/unsigned.apk build/aligned.apk
: "${ESTOQUEBIP_KEYSTORE_PASSWORD:?Configure a senha da chave de assinatura}"
"$BT/apksigner" sign --ks "${ESTOQUEBIP_KEYSTORE:-signing/estoquebip.jks}" --ks-pass env:ESTOQUEBIP_KEYSTORE_PASSWORD --out EstoqueBip.apk build/aligned.apk
"$BT/apksigner" verify --verbose EstoqueBip.apk
