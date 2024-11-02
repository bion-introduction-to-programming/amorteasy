package com.example.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Kelas Dasar (Parent Class) untuk Penyusutan
class KalkulatorPenyusutan {
    private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

    protected String jenisHarta;
    protected String kelompokHarta;
    protected String metodePenyusutan;
    protected double hargaPerolehan;
    protected double tarifPenyusutan;

    public KalkulatorPenyusutan(String jenisHarta, String kelompokHarta, String metodePenyusutan, double hargaPerolehan) {
        this.jenisHarta = jenisHarta;
        this.kelompokHarta = kelompokHarta;
        this.metodePenyusutan = metodePenyusutan;
        this.hargaPerolehan = hargaPerolehan;
    }

    private double tarifPenyusutan() {
        double tarifPenyusutan;
        if (this.metodePenyusutan.equals("Garis lurus")) {
            tarifPenyusutan = switch (this.kelompokHarta) {
                case "Kelompok 1" -> 0.25;
                case "Kelompok 2" -> 0.125;
                case "Kelompok 3" -> 0.0625;
                case "Kelompok 4" -> 0.05;
                default -> 0;
            };
        } else {
            tarifPenyusutan = switch (this.kelompokHarta) {
                case "Kelompok 1" -> 0.50;
                case "Kelompok 2" -> 0.25;
                case "Kelompok 3" -> 0.125;
                case "Kelompok 4" -> 0.10;
                default -> 0;
            };
        }
        return tarifPenyusutan;
    }

    // Method umum untuk menghitung penyusutan
    public double hitungPenyusutan() {
        log.info(this.jenisHarta);
        log.info(this.kelompokHarta);
        log.info(this.metodePenyusutan);
        log.info(String.valueOf(this.hargaPerolehan));
        log.info(String.valueOf(this.tarifPenyusutan));
        tarifPenyusutan = tarifPenyusutan();
        return hargaPerolehan * tarifPenyusutan;
    }
}

