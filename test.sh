#!/usr/bin/env bash
set -euo pipefail
mkdir -p build/tests
javac -source 8 -target 8 -encoding UTF-8 -classpath lib/zxing.jar -d build/tests src/br/com/estoquebip/LocationCatalog.java src/br/com/estoquebip/PackCatalog.java src/br/com/estoquebip/ScanGeometry.java src/br/com/estoquebip/BarcodeDecoder.java tests/br/com/estoquebip/CoreChecks.java
java -cp build/tests:lib/zxing.jar br.com.estoquebip.CoreChecks
