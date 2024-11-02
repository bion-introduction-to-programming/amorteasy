package com.example.crudwithvaadin;

// Kelas Turunan untuk Harta Berwujud
class PenyusutanHartaBerwujud extends KalkulatorPenyusutan {
    public PenyusutanHartaBerwujud(String kelompokHarta, String metodePenyusutan, double hargaPerolehan) {
        super("Harta berwujud", kelompokHarta, metodePenyusutan, hargaPerolehan);  // Memanggil konstruktor parent class
    }

    @Override
    public double hitungPenyusutan() {
        // Penyusutan khusus untuk harta berwujud
        return super.hitungPenyusutan();
    }
}

