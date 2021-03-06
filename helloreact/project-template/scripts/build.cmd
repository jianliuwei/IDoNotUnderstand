@echo off

echo babel compiling...
call node_modules\.bin\babel js/source -d js/build

echo browserify bundling...
call node_modules\.bin\browserify js/build/app.js -o bundle.js
echo browserify bundling... DONE

echo css bundling...
call type css\components\* css\* > bundle.css
echo css bundling... DONE

PowerShell -NoProfile -ExecutionPolicy Bypass -Command "(Get-Content bundle.css).replace('../../images', 'images') | Set-Content bundle.css"

echo Build DONE %date%%time%

@echo on