#!/bin/sh
set -eu
cd "$(dirname "$0")"
for target in 26.1.2 26.2 26.3; do
    ./gradlew "-PmcVersion=$target" build
done
