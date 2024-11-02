package com.example.crudwithvaadin;

// Kelas Turunan untuk Harta Tidak Berwujud
class PenyusutanHartaTidakBerwujud extends KalkulatorPenyusutan {
    public PenyusutanHartaTidakBerwujud(String kelompokHarta, String metodePenyusutan, double hargaPerolehan) {
        super("Harta tidak berwujud", kelompokHarta, metodePenyusutan, hargaPerolehan);  // Memanggil konstruktor parent class
    }

    @Override
    public double hitungPenyusutan() {
        // Penyusutan khusus untuk harta tidak berwujud
        return super.hitungPenyusutan();
    }
}

