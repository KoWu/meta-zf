DESCRIPTION = "Automatically update linux if a USB stick is plugged in"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
RDEPENDS_${PN} = "sw-update bash"

PV = "1.0.0"

SRC_URI = " \
  file://71-automount.rules \
  file://automountupdate.sh \
  file://device-automount@.service \
  "

do_install () {
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/71-automount.rules ${D}/${sysconfdir}/udev/rules.d
    
    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/device-automount@.service ${D}${systemd_system_unitdir}
    
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/automountupdate.sh ${D}/${bindir}
}

FILES_${PN} = "${sysconfdir}/udev/* \
               ${bindir}/* \
               ${systemd_system_unitdir}/* \
               "
