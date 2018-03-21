FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
		file://add-me909s-support.patch \
		file://disable-dhcp-on-me909u.patch \
		"

do_install_append_am335x-nbhw16-fct() {
    sed -i 's/ExecStart=.*/ExecStart=\/usr\/sbin\/ModemManager --debug/g' ${D}/lib/systemd/system/ModemManager.service
}
