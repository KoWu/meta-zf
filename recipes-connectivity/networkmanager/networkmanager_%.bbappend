FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
        file://ethernet-2a2b6485-4d06-4b86-8051-751399c6881a \
        file://NetworkManager.conf \
        "

do_install_append() {
    mkdir -p ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/ethernet-2a2b6485-4d06-4b86-8051-751399c6881a ${D}${sysconfdir}/NetworkManager/system-connections/
    install -m 0600 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/
}
