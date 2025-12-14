package fr.yak.holy.modele;

import java.time.LocalDate;

public class MotDePasse {
    private String valMdp;
    private LocalDate dateMdp;

    public MotDePasse(String valMdp, LocalDate dateMdp) {
        this.valMdp = valMdp;
        this.dateMdp = dateMdp;
    }

    public String getValMdp() { return valMdp; }
}