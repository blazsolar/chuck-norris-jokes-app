#!/bin/bash

export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH"

pushd YourTestApp

# clear the logs
adb logcat -c

# run tests and check output
python - << END
import re
import subprocess as sp
import sys
import threading
import time

done = False

def update():
  # prevent CircleCI from killing the process for inactivity
  while not done:
    time.sleep(5)
    print "Running..."

t = threading.Thread(target=update)
t.dameon = True
t.start()

def run():
  sp.Popen(['adb', 'wait-for-device']).communicate()
  p = sp.Popen('adb shell am instrument -w com.myapp.test/android.test.InstrumentationTestRunner',
               shell=True, stdout=sp.PIPE, stderr=sp.PIPE, stdin=sp.PIPE)
  return p.communicate()

success = re.compile(r'OK \(\d+ tests\)')
stdout, stderr = run()

done = True
print stderr
print stdout

if success.search(stderr + stdout):
  sys.exit(0)
else:
  sys.exit(1) # make sure we fail if the test failed
END

RETVAL=$?

# dump the logs
adb logcat -d

popd
exit $RETVAL
