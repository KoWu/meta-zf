inherit core-image

SUMMARY = "nmrouter image supporting can and gps"

KERNEL_IMAGETYPE = "uImage"

IMAGE_FSTYPES_append = " tar.gz "

IMAGE_FEATURES_append = " \
                package-management \
                ssh-server-openssh \
		tools-debug \
                "

FIRMWARE ?= "linux-firmware"
FIRMWARE_am335x-nbhw16 = " \
                linux-firmware-ath6k \
                linux-firmware-ath9k \
                linux-firmware-wl12xx\
                linux-firmware-wl18xx\
                "

PYTHON_PACKAGES = " \
                python-misc \
                python3-misc \
                python-ctypes \
                python3-ctypes \
                python-can \
                python3-can \
                python-gps3 \
                python3-gps3 \
                python-pip \
                python3-pip \
              "

IMAGE_INSTALL_append = " \
                hostapd \
                iw \
                crda \
                wpa-supplicant \
                openssh-sftp-server \
                bash \
                iproute2 \
                ethtool \
                devmem2 \
                openvpn \
                iptables \
                pciutils \
                kernel-modules \
                kernel-devicetree \
                python-subprocess \
                networkmanager \
                modemmanager \
                board-descriptor \
                sw-update \
                python-pip \
                rng-tools \
                glibc-utils \
                glibc-gconv \
                glibc-gconv-utf-16 \
                glibc-gconv-utf-32 \
                bridge-utils \
                gpsd \
                ${FIRMWARE} \
                "

IMAGE_INSTALL_append_am335x-nbhw16 = " \
                tibluetooth \
                bluez5-obex \
                bluez5-noinst-tools \
                can-utils \
                wakeup-timer \
                libftdi \
                lrzsz \
                socat \
                nano \
                tcpdump \
                python3 \
                ${PYTHON_PACKAGES} \
                cangps-files \
                auto-update \
                libmodbus \
                canopensocket \
                mc \
                "

LICENSE = "BSD"

