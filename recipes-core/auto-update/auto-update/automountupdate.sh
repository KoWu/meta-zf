#!/bin/bash

#------------------------------------------------------------
# this script is called by systemd, triggered through udev.
# it mounts usb storage to /mnt and updates linux if necessary.
#
# usage: automountupdate.sh [device]
#------------------------------------------------------------

if [ $# -ne 1 ]
then
    echo -e "invalid number of arguments, misusage."
    exit 1
fi

function blink {
    while true
    do
        echo 1 > /sys/class/leds/nb\:green\:leda/brightness
        usleep 50000
        echo 0 > /sys/class/leds/nb\:green\:leda/brightness
        usleep 50000
    done
}

mkdir -p /mnt/$1
mount /dev/$1 /mnt/$1
echo "mounted USB device"
if [ -f /mnt/$1/image.tar.gz ] && ! grep -Fxq $(cat /sys/class/net/eth0/address) /mnt/$1/.updated 2> /dev/null
then
    echo "updating now!"
    blink &
    BLINKPID=$!
    sw-update.sh -l /mnt/$1/image.tar.gz
    echo $(cat /sys/class/net/eth0/address) >> /mnt/$1/.updated
    kill $BLINKPID > /dev/null 2>&1
    echo 0 > /sys/class/leds/nb\:green\:leda/brightness
    echo "done. rebooting now."
    umount /mnt/$1
    reboot now
else
    echo "no updates found or already updated"
fi

exit 0
