package com.example.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

	private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

	IntegerField tahunFiskal;
	MonthField akhirBulanTahunBuku;
	Select<String> jenisHarta;
	Select<String> kelompokHarta;
	Select<String> metodePenyusutan;
	MonthField bulanTahunPerolehan;
	NumberField hargaPerolehan;
	Button hitung;
	ConfirmDialog dialog;
	ConfirmDialog alert;
	private KalkulatorPenyusutan kalkulatorPenyusutan;

	public MainView() {
		this.tahunFiskal = new IntegerField("Tahun Fiskal / Tahun SPT");
		this.tahunFiskal.setWidth("6em");
		this.tahunFiskal.setRequiredIndicatorVisible(true);
		this.tahunFiskal.addValueChangeListener(e -> ubahTahun(e.getValue()));
		add(this.tahunFiskal);

		// this.akhirBulanTahunBuku = new MonthField("Akhir Bulan dan Tahun Buku");
		// add(this.akhirBulanTahunBuku);

		this.jenisHarta = new Select<>();
		this.jenisHarta.setLabel("Jenis Harta");
		this.jenisHarta.setItems("Harta berwujud", "Harta tidak berwujud");
		this.jenisHarta.setRequiredIndicatorVisible(true);
		this.jenisHarta.setWidth("18.6em");
		add(this.jenisHarta);

		this.kelompokHarta = new Select<>();
		this.kelompokHarta.setLabel("Kelompok Harta");
		this.kelompokHarta.setItems("Kelompok 1", "Kelompok 2", "Kelompok 3", "Kelompok 4");
		this.kelompokHarta.setRequiredIndicatorVisible(true);
		this.kelompokHarta.setWidth("18.6em");
		add(this.kelompokHarta);

		this.metodePenyusutan = new Select<>();
		this.metodePenyusutan.setLabel("Metode Penyusutan Fiskal");
		this.metodePenyusutan.setItems("Garis lurus", "Saldo menurun");
		this.metodePenyusutan.setRequiredIndicatorVisible(true);
		this.metodePenyusutan.setWidth("18.6em");
		add(this.metodePenyusutan);
		
		this.bulanTahunPerolehan = new MonthField("Bulan dan Tahun Perolehan");
		add(this.bulanTahunPerolehan);

		this.hargaPerolehan = new NumberField();
		hargaPerolehan.setLabel("Harga Perolehan");
		Div prefix = new Div();
		prefix.setText("Rp");
		hargaPerolehan.setPrefixComponent(prefix);
		this.hargaPerolehan.setRequiredIndicatorVisible(true);
		add(this.hargaPerolehan);

		this.hitung = new Button("Hitung", VaadinIcon.PENCIL.create());
		this.hitung.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		this.hitung.addClickListener(e -> hitung());
		add(this.hitung);

		this.dialog = new ConfirmDialog();
		this.dialog.setHeader("Penyusutan");
		this.dialog.setText("Rp ");
		this.dialog.setConfirmText("OK");

		this.alert = new ConfirmDialog();
		this.alert.setHeader("Perhatian");
		this.alert.setText("Rp ");
		this.alert.setConfirmText("OK");
		this.alert.setConfirmButtonTheme("error primary");
	}

	void ubahTahun(Integer tahun) {
		// this.akhirBulanTahunBuku.setYear(tahun);
		this.bulanTahunPerolehan.setYear(tahun);
	}
	
	void hitung() {
		try {
			// Validasi input kosong
			if (this.tahunFiskal.isEmpty() && this.jenisHarta.isEmpty() && this.kelompokHarta.isEmpty() && this.metodePenyusutan.isEmpty() && this.hargaPerolehan.isEmpty()) {
				throw new IllegalArgumentException("Semua field harus diisi!");
			}
			log.info("validasi ok");
			log.info(this.hargaPerolehan.getValue().toString());

			// Memilih kalkulator penyusutan berdasarkan jenis harta
			if (this.jenisHarta.equals("Harta Berwujud")) {
				this.kalkulatorPenyusutan = new PenyusutanHartaBerwujud(this.kelompokHarta.getValue(), this.metodePenyusutan.getValue(), this.hargaPerolehan.getValue());
			} else {
				this.kalkulatorPenyusutan = new PenyusutanHartaTidakBerwujud(this.kelompokHarta.getValue(), this.metodePenyusutan.getValue(), this.hargaPerolehan.getValue());
			}
			log.info("pilih kalkulator ok");

			// Menggunakan polymorphism untuk menghitung penyusutan
			double hasilPenyusutan = this.kalkulatorPenyusutan.hitungPenyusutan();
			log.info("polymorphismi ok");

			// Menampilkan hasil penyusutan
			dialog.setText("Rp " + hasilPenyusutan);
			log.info("tampilkan hasil");

			this.dialog.open();
        } catch (IllegalArgumentException e) {
			this.alert.setText(e.getMessage());
			this.alert.open();
        } catch (Exception e) {
			this.alert.setText("Terjadi kesalahan: " + e.getMessage());
            this.alert.open();
        }
	}
}
