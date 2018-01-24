FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
  file://nbhw16_zf_defconfig \
  "

KERNEL_DEFCONFIG = "nbhw16_zf_defconfig"

do_pre_configure() {
 	   cp ${WORKDIR}/nbhw16_zf_defconfig ${S}/arch/arm/configs/nbhw16_zf_defconfig
}
addtask pre_configure before do_configure after do_patch
