#!/bin/sh
if [ -d data ]; then
	echo "Please move data dir first! Abort."
	exit 1
fi
rsync -va --rsh='ssh -p 38556' root@shi-idp1.iubh.de:/var/cache/pwm .
mv pwm data
