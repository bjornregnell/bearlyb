{pkgs ? import (
    fetchTarball "https://github.com/NixOS/nixpkgs/archive/647e5c14cbd5067f44ac86b74f014962df460840.tar.gz"
  ) {}
}:
pkgs.mkShellNoCC {
  packages = with pkgs; [
    jdk21
    scala-next
    scala-cli
    mill
  ];
}
