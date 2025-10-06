{pkgs ? import (
    fetchTarball "https://github.com/NixOS/nixpkgs/archive/647e5c14cbd5067f44ac86b74f014962df460840.tar.gz"
  ) {}
}:
let
  runtimeLibs = with pkgs; lib.makeLibraryPath [
    # xorg.libX11
    # xorg.libXcursor
    # xorg.libXext
    # xorg.libXrandr
    # xorg.libXxf86vm
    wayland
    libxkbcommon
  ];
in
  pkgs.mkShell {
    shellHook = ''
      export LD_LIBRARY_PATH=${runtimeLibs}:$LD_LIBRARY_PATH
    '';
    packages = with pkgs; [
      jdk21
      scala-next
      scala-cli
      mill
    ];
  }
