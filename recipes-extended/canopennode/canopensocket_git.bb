SUMMARY = "Linux CANopen tools"
DESCRIPTION = "Collection of CANopen tools running on Linux with socketCAN interface."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75859989545e37968a99b631ef42722e"

SRC_URI = "gitsm://github.com/CANopenNode/CANopenSocket.git;protocol=https"
SRCREV = "6606856c76f5ac13f8db8d22766b078803af736c"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    cd ${S}/canopend
    make
    cd ${S}/canopencomm
    make
    cd ${S}/canopencgi
    make
}

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/canopend/app/canopend ${D}${bindir}
    install -m 0755 ${S}/canopencomm/canopencomm ${D}${bindir}
    install -m 0755 ${S}/canopencgi/canopen.cgi ${D}${bindir}
}
