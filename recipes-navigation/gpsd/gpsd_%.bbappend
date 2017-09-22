FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG = ""

do_install_append() {
    rm ${D}/${sysconfdir}/udev/rules.d/60-gpsd.rules
    rm ${D}${base_libdir}/udev/gpsd.hotplug
    rm ${D}${systemd_unitdir}/system/${BPN}.socket
}

RRECOMMENDS_${PN}_remove = "gpsd-udev"
PACKAGES_remove = "gpsd-udev"
FILES_gps-udev= ""

SYSTEMD_SERVICE_${PN} = "${PN}.service"
