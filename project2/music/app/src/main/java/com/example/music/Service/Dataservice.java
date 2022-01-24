package com.example.music.Service;

import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
import com.example.music.Model.Chude;
import com.example.music.Model.Chudetheloai;
import com.example.music.Model.Playlist;
import com.example.music.Model.Quangcao;
import com.example.music.Model.Theloai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dataservice {
    @POST("songbanner.php")
    Call<List<Quangcao>> getDataBanner();

    @POST("playlistforcurrentday.php")
    Call<List<Playlist>> getDataPlayList();

    @POST("chudetheloai.php")
    Call<Chudetheloai>  getCateGoryMusic();

    @POST("albumhot.php")
    Call<List<Album>> getAlbumHot();

    @POST("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatYeuThich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoQuangCao(@Field("idQuangCao") String idQuangCao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idPlayList") String idPlayList);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idTheLoai") String idTheLoai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoAlbum(@Field("idAlbum") String idAlbum);

    @POST("danhsachplaylist.php")
    Call<List<Playlist>> getDanhSachPlayList();

    @POST("danhsachalbum.php")
    Call<List<Album>> getDanhSachAlbum();

    @POST("danhsachchude.php")
    Call<List<Chude>> getDanhSachChuDe();

    @FormUrlEncoded
    @POST("danhsachtheloaitheochude.php")
    Call<List<Theloai>> getDanhSachTheLoaiTheoChuDe(@Field("idChuDe") String idChuDe);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<BaiHat>> getDanhSachTimKiem(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);
}
