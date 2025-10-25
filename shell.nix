{
  pkgs ? import (
    fetchTarball "https://github.com/NixOS/nixpkgs/archive/647e5c14cbd5067f44ac86b74f014962df460840.tar.gz"
  ) {}
}:
let
  stdenv = pkgs.stdenv;
  lib = pkgs.lib;
  config = pkgs.config;

  alsaSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  dbusSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  drmSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  ibusSupport = stdenv.hostPlatform.isUnix && !stdenv.hostPlatform.isDarwin;
  jackSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  libdecorSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  openglSupport = lib.meta.availableOn stdenv.hostPlatform pkgs.libGL;
  pipewireSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  pulseaudioSupport =
    config.pulseaudio or stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  libudevSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  sndioSupport = false;
  traySupport = true;
  waylandSupport = stdenv.hostPlatform.isLinux && !stdenv.hostPlatform.isAndroid;
  x11Support = !stdenv.hostPlatform.isAndroid && !stdenv.hostPlatform.isWindows;

  dlopenBuildInputs = with pkgs;
    lib.optionals stdenv.hostPlatform.isLinux [
      libusb1
    ]
    ++ lib.optional (
      stdenv.hostPlatform.isUnix && !stdenv.hostPlatform.isDarwin
    ) libayatana-appindicator
    ++ lib.optional alsaSupport alsa-lib
    ++ lib.optional dbusSupport dbus
    ++ lib.optionals drmSupport [
      libdrm
      libgbm
    ]
    ++ lib.optional jackSupport libjack2
    ++ lib.optional libdecorSupport libdecor
    ++ lib.optional libudevSupport systemdLibs
    ++ lib.optional openglSupport libGL
    ++ lib.optional pipewireSupport pipewire
    ++ lib.optional pulseaudioSupport libpulseaudio
    ++ lib.optional sndioSupport sndio
    ++ lib.optionals waylandSupport [
      libxkbcommon
      wayland
    ]
    ++ lib.optionals x11Support [
      xorg.libX11
      xorg.libXScrnSaver
      xorg.libXcursor
      xorg.libXext
      xorg.libXfixes
      xorg.libXi
      xorg.libXrandr
    ]
    ++ [
      vulkan-headers
      vulkan-loader
    ]
    ++ lib.optional (openglSupport && !stdenv.hostPlatform.isDarwin) libGL
    ++ lib.optional x11Support xorg.libX11;
in
  assert lib.assertMsg (
    waylandSupport -> openglSupport
  ) "SDL3 requires OpenGL support to enable Wayland";
  assert lib.assertMsg (
    ibusSupport -> dbusSupport
  ) "SDL3 requires dbus support to enable ibus";

  pkgs.mkShell rec {
    buildInputs = dlopenBuildInputs;

    # Many dependencies are not directly linked to, but dlopen()'d at runtime. Adding them to the RPATH
    # helps them be found
    LD_LIBRARY_PATH = lib.optionalString (
      stdenv.hostPlatform.hasSharedLibraries && stdenv.hostPlatform.extensions.sharedLibrary == ".so"
    ) "-rpath ${lib.makeLibraryPath (dlopenBuildInputs)}";

    packages = with pkgs; [
      jdk21
      scala-next
      scala-cli
      mill
    ];
  }
