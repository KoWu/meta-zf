SUMMARY = "Python 2.7-3.5 clients for gpsd"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b3f4cc03cb8549623b4bee89feebe73"

SRC_URI = "git://github.com/keigezellig/gps3.git;protocol=https"
SRCREV ?= "b66d48915a425ef1c83f3cafa566fb0ade14d5f6"

S = "${WORKDIR}/git"

do_install_append() {
    rm -rf ${D}/usr/share
}

PYPI_PACKAGE="gps3"
