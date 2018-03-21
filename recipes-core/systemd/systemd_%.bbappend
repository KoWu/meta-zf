FILESEXTRAPATHS_prepend := "${THISDIR}/${MACHINE}/${PN}:${THISDIR}/${PN}:"

PACKAGECONFIG = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'efi ldconfig pam selinux usrmerge', d)} \
    xz \
"

SRC_URI_append = " \
                 file://system.conf \
                 "

do_install_append() {
    mkdir -p ${D}/etc/systemd/
    cp ${WORKDIR}/system.conf ${D}/etc/systemd
}
