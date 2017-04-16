package com.rpl.rplku;

/**
 * Created by Fathur on 4/14/2017.
 */

public class Config {

    //Address dari script CRUD
    public static final String URL_ADD = "http://fathuriady.esy.es/rpl/addBarang.php";
    public static final String URL_GETALL = "http://fathuriady.esy.es/rpl/getAllBarang.php";
    public static final String URL_GETBARANG = "http://fathuriady.esy.es/rpl/getBarang.php?id=";
    public static final String URL_UPDATE_BARANG = "http://fathuriady.esy.es/rpl/updateBarang.php";
    public static final String URL_DELETE_BARANG = "http://fathuriady.esy.es/rpl/deleteBarang.php?id=";

    //Key buat request ke PHP Script
    public static final String KEY_BARANG_ID = "id";
    public static final String KEY_BARANG_NAMA = "nama_barang";
    public static final String KEY_BARANG_JML = "jml_barang";

    //Tag JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA_BARANG = "nama_barang";
    public static final String TAG_JML_BARANG = "jml_barang";

    //ID barang untuk di pass antar intent
    public static final String BARANG_ID = "barang_id";

}
