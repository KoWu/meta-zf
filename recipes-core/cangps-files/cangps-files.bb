DESCRIPTION = "Provides additional files for the cangps image."
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

PV = "1.0.0"

SRC_URI = " \
  file://44-socketcan.rules \
  file://socketcan@.service \
  file://socketcan \
  file://gps_to_can.py \
  file://README \
  "

S = "${WORKDIR}"

do_install () {
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/44-socketcan.rules ${D}/${sysconfdir}/udev/rules.d

    install -d ${D}/${sysconfdir}/default
    install -m 0644 ${WORKDIR}/socketcan ${D}/${sysconfdir}/default
    
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/socketcan@.service ${D}${systemd_system_unitdir}
    
    install -d ${D}${ROOT_HOME}
    install -m 0777 ${WORKDIR}/gps_to_can.py ${D}${ROOT_HOME}
    install -m 0644 ${WORKDIR}/README ${D}${ROOT_HOME}
}

FILES_${PN} = "${sysconfdir}/udev/* \
               ${sysconfdir}/default/* \
               ${systemd_system_unitdir}/* \
               ${ROOT_HOME}/* \
               "
