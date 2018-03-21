# rocko release! meta-nmrouter independent!
# 1. Get required files
```sh
$ git clone -b rocko git://git.yoctoproject.org/poky
$ cd poky
$ git clone -b rocko git://git.openembedded.org/meta-openembedded
$ git clone -b rocko https://github.com/KoWu/meta-zf.git
```
# 2. Setup build for nmrouter-image
We call our build directory nb800.
```sh
$ source oe-init-build-env nb800
```
Modify *conf/local.conf*
```sh
MACHINE = "am335x-nbhw16"
DISTRO = "nmrouter"
```
Modify *conf/bblayers.conf*
```sh
BBLAYERS ?= " \
  <poky dir>/meta \
  <poky dir>/meta-poky \
  <poky dir>/meta-zf \
  <poky dir>/meta-openembedded/meta-python \
  <poky dir>/meta-openembedded/meta-oe \
  <poky dir>/meta-openembedded/meta-networking \
  "
```
# 3. Build
```sh
$ source oe-init-build-env nb800
$ bitbake nmrouter-image-rocko
```
Output images can be found in *<poky dir>/nb800/tmp/deploy/images/am335x-nbhw16/nmrouter-image-rocko-am335x-nbhw16.tar.gz*
