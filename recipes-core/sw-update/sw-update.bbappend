FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	file://0001-no-delete-force-mkfs.patch;patchdir=${WORKDIR} \
	"

