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
in
  assert lib.assertMsg (
    waylandSupport -> openglSupport
  ) "SDL3 requires OpenGL support to enable Wayland";
  assert lib.assertMsg (
    ibusSupport -> dbusSupport
  ) "SDL3 requires dbus support to enable ibus";

  pkgs.mkShell rec {
    shellHook = ''
      export LD_LIBRARY_PATH=${lib.makeLibraryPath (buildInputs ++ nativeBuildInputs)}:$LD_LIBRARY_PATH
    '';

    nativeBuildInputs = with pkgs; [] ++ lib.optional waylandSupport wayland-scanner;

    buildInputs = with pkgs;
      lib.optionals stdenv.hostPlatform.isLinux [
        libusb1
      ]
      ++ lib.optional (
        stdenv.hostPlatform.isUnix && !stdenv.hostPlatform.isDarwin && traySupport
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
        xorg.libxcb
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
      ++ lib.optional x11Support xorg.libX11
      ++ lib.optionals stdenv.hostPlatform.isDarwin [
        # error: 'MTLPixelFormatASTC_4x4_LDR' is unavailable: not available on macOS
        (darwinMinVersionHook "11.0")

        apple-sdk_11
      ]
      ++ lib.optionals ibusSupport [
        # sdl3 only uses some constants of the ibus headers
        # it never actually loads the library
        # thus, it also does not have to care about gtk integration,
        # so using ibusMinimal avoids an unnecessarily large closure here.
        ibusMinimal
      ];

    packages = with pkgs; [
      jdk21
      scala-next
      scala-cli
      mill
    ];
  }
