FILESEXTRAPATHS_prepend := "${THISDIR}/${MACHINE}/${PN}:${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://system.conf \
	"

do_install_append() {
    mkdir -p ${D}/etc/systemd/
    cp ${WORKDIR}/system.conf ${D}/etc/systemd
    ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttyS0.service
    ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttyS1.service
    ln -s /dev/null ${D}/etc/systemd/system/getty@tty1.service
}
