@echo off
setlocal
pushd "%~dp0"
for %%V in (26.1.2 26.2 26.3) do (
    call gradlew.bat -PmcVersion=%%V build
    if errorlevel 1 goto failed
)
popd
exit /b 0
:failed
popd
exit /b 1
