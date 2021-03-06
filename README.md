# 1. Get required files
```sh
$ git clone -b jethro git://git.yoctoproject.org/poky
$ cd poky
$ git clone -b jethro git://git.openembedded.org/meta-openembedded
$ git clone -b jethro https://github.com/netmodule/meta-nmrouter.git
$ git clone -b jethro https://github.com/KoWu/meta-zf.git
```

# 2. Setup build for nmrouter-image
We call our build directory nb800. The command will automatically cd into nb800.
```sh
$ source oe-init-build-env nb800
```
Modify *conf/local.conf*
```sh
MACHINE = "am335x-nbhw16"
DISTRO = "nmrouter"
```
Modify *conf/bblayers.conf*
Only add *<poky dir>/meta-zf* if you want to build the modified cangps image.
```sh
BBLAYERS ?= " \
  <poky dir>/meta \
  <poky dir>/meta-yocto \
  <poky dir>/meta-nmrouter \
  <poky dir>/meta-zf \
  <poky dir>/meta-openembedded/meta-python \
  <poky dir>/meta-openembedded/meta-oe \
  <poky dir>/meta-openembedded/meta-networking \
  "
```

# 3. Build
```sh
$ source oe-init-build-env nb800
$ bitbake nmrouter-image
```
or, for the cangps image, simply run
```sh
$ bitbake nmrouter-image-cangps
```
Output images can be found in *<poky dir>/nb800/tmp/deploy/images/am335x-nbhw16/<image>-<machine>.tar.gz*
To save HDD space delete the entire *<poky dir>/nb800/tmp/* directory. Compiled packages are stored inside *sstate-cache*.

# 3.1 Modify linux (canpgs)
To menuconfig, use
```sh
$ bitbake linux-netmodule -c menuconfig
```
The generated *.config* can be found in */tmp/work/am335x_nbhw16-netmodule-linux-gnueabi/linux-netmodule/.../build/.config*
Copy the config to */meta-zf/recipes-kernel/linux/linux-netmodule/* and rename it to *nbhw16_\*_defconfig*.
Edit the *linux-netmodule_%.bbappend* accordingly.


# 4. Flash
Use the integrated sw-update script
```sh
$ sw-update.sh -l <image>-<machine>.tar.gz
```
