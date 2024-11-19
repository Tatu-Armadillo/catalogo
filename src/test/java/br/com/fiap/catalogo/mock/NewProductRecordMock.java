package br.com.fiap.catalogo.mock;

import br.com.fiap.catalogo.record.product.NewProductRecord;

public class NewProductRecordMock {

    public static NewProductRecord mock() {
        return new NewProductRecord(
                "name",
                1.0D,
                "modelo",
                "fabricante",
                "detalhes");
    }

}
