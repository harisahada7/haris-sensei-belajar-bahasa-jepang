package com.example.data.model

data class VocabularyItem(
    val japanese: String,
    val romaji: String,
    val meaning: String,
    val notes: String
)

data class TopicCategory(
    val name: String,
    val description: String,
    val iconName: String,
    val items: List<VocabularyItem>
)

object VocabularyData {
    val categories = listOf(
        TopicCategory(
            name = "Salam & Perkenalan",
            description = "Ekspresi dasar untuk menyapa orang lain dan memperkenalkan diri.",
            iconName = "waving_hand",
            items = listOf(
                VocabularyItem("こんにちは", "Konnichiwa", "Halo / Selamat Siang", "Diucapkan pada siang hari secara umum."),
                VocabularyItem("おはようございます", "Ohayou gozaimasu", "Selamat Pagi", "Bentuk sopan, gunakan 'Ohayou' kepada teman sebaya."),
                VocabularyItem("こんばんは", "Konbanwa", "Selamat Malam", "Diucapkan saat hari mulai gelap atau malam hari."),
                VocabularyItem("おやすみなさい", "Oyasuminasai", "Selamat Tidur / Selamat Malam", "Diucapkan sebelum tidur atau berpisah di malam hari."),
                VocabularyItem("はじめまして", "Hajimemashite", "Senang bertemu dengan Anda", "Diucapkan pertama kali saat berkenalan (dari kata 'awal')."),
                VocabularyItem("よろしくおねがいします", "Yoroshiku onegaishimasu", "Mohon bantuannya / Mohon bimbingannya", "Ungkapan khas Jepang di akhir perkenalan."),
                VocabularyItem("お元気ですか", "O-genki desu ka", "Apa kabar?", "Menanyakan kesehatan lawan bicara."),
                VocabularyItem("元気です", "Genki desu", "Saya sehat / Kabar baik", "Jawaban standar untuk O-genki desu ka."),
                VocabularyItem("ありがとうございます", "Arigatou gozaimasu", "Terima kasih banyak", "Gunakan 'Arigatou' untuk percakapan akrab."),
                VocabularyItem("すみません", "Sumimasen", "Permisi / Maaf", "Sangat multifungsi untuk memanggil pelayan atau minta maaf.")
            )
        ),
        TopicCategory(
            name = "Angka & Waktu",
            description = "Mempelajari cara berhitung dan menyampaikan waktu dalam bahasa Jepang.",
            iconName = "numbers",
            items = listOf(
                VocabularyItem("一 (いち)", "Ichi", "Satu (1)", "Angka dasar."),
                VocabularyItem("二 (に)", "Ni", "Dua (2)", "Angka dasar."),
                VocabularyItem("三 (さん)", "San", "Tiga (3)", "Angka dasar."),
                VocabularyItem("四 (よん / し)", "Yon / Shi", "Empat (4)", "Sering dihindari penulisan 'Shi' karena mirip kata 'Kematian' (Shinu)."),
                VocabularyItem("五 (ご)", "Go", "Lima (5)", "Angka dasar."),
                VocabularyItem("六 (ろく)", "Roku", "Enam (6)", "Angka dasar."),
                VocabularyItem("七 (なな / しち)", "Nana / Shichi", "Tujuh (7)", "Lebih sering menggunakan 'Nana'."),
                VocabularyItem("八 (はち)", "Hachi", "Delapan (8)", "Angka dasar."),
                VocabularyItem("九 (きゅう)", "Kyuu", "Sembilan (9)", "Angka dasar."),
                VocabularyItem("十 (じゅう)", "Juu", "Sepuluh (10)", "Angka dasar."),
                VocabularyItem("何時ですか", "Nan-ji desu ka", "Jam berapa?", "Menanyakan waktu saat ini."),
                VocabularyItem("一時 (いちじ)", "Ichi-ji", "Jam satu", "Jumlah jam di Jepang menggunakan akhiran ~ji.")
            )
        ),
        TopicCategory(
            name = "Makanan & Restoran",
            description = "Kata-kata penting saat memesan atau makan di restoran Jepang.",
            iconName = "restaurant",
            items = listOf(
                VocabularyItem("いただきます", "Itadakimasu", "Selamat makan (saya menerima)", "Diucapkan sebelum makan sebagai wujud rasa bersyukur."),
                VocabularyItem("ごちそうさまでした", "Gochisousama deshita", "Terima kasih atas hidangannya", "Diucapkan setelah selesai makan."),
                VocabularyItem("メニュー", "Menyuu", "Menu masakan", "Kata serapan dari Bahasa Inggris."),
                VocabularyItem("美味しいです", "Oishiidesu", "Enak / Lezat", "Ungkapan apresiasi rasa makanan."),
                VocabularyItem("水 (みず)", "Mizu", "Air putih", "Air putih biasa atau air minum dingin."),
                VocabularyItem("これをお願いします", "Kore wo onegaishimasu", "Minta yang ini", "Sambil menunjuk menu saat memesan."),
                VocabularyItem("お勘定をお願いします", "O-kanjou wo onegaishimasu", "Minta bon tagihannya", "Digunakan di kasir atau di meja makan."),
                VocabularyItem("辛い", "Karai", "Pedas", "Sangat berguna bagi turis Indonesia yang suka makanan pedas.")
            )
        ),
        TopicCategory(
            name = "Percakapan Sehari-hari",
            description = "Frasa praktis yang sering digunakan sehari-hari di Jepang.",
            iconName = "chat",
            items = listOf(
                VocabularyItem("はい", "Hai", "Ya", "Digunakan untuk setuju atau mengonfirmasi."),
                VocabularyItem("いいえ", "Iie", "Tidak", "Digunakan secara sopan untuk menolak atau menyangkal."),
                VocabularyItem("わかりました", "Wakarimashita", "Saya mengerti / Paham", "Bentuk lampau dari wakarimasu (sekarang sudah paham)."),
                VocabularyItem("わかりません", "Wakarimasen", "Saya tidak mengerti", "Sangat berguna saat bingung di Jepang."),
                VocabularyItem("ちょっと待ってください", "Chotto matte kudasai", "Tolong tunggu sebentar", "Sering digunakan pelayan toko atau pemandu."),
                VocabularyItem("大丈夫です", "Daijoubu desu", "Tidak apa-apa / Aman / OK", "Ekspresi sangat serbaguna untuk menolak halus atau mengonfirmasi."),
                VocabularyItem("お疲れ様でした", "Otsukaresama deshita", "Terima kasih kerja kerasnya", "Diucapkan setelah menyelesaikan pekerjaan bersama."),
                VocabularyItem("どこですか", "Doko desu ka", "Di mana?", "Menanyakan keberadaan suatu tempat atau barang.")
            )
        ),
        TopicCategory(
            name = "Liburan & Perjalanan",
            description = "Frasa untuk memandu Anda saat jalan-jalan atau pergi antar tempat.",
            iconName = "flight_takeoff",
            items = listOf(
                VocabularyItem("切符 (きっぷ)", "Kippu", "Tiket", "Tiket kereta, bus, atau wahana."),
                VocabularyItem("駅 (えき)", "Eki", "Stasiun", "Tempat naik kereta di Jepang."),
                VocabularyItem("トイレはどこですか", "Toire wa doko desu ka", "Ada di mana toiletnya?", "Pertanyaan darurat yang wajib dihafal!"),
                VocabularyItem("これを買います", "Kore wo kaimasu", "Saya beli yang ini", "Diucapkan saat berbelanja."),
                VocabularyItem("いくらですか", "Ikura desu ka", "Berapa harganya?", "Menanyakan harga barang."),
                VocabularyItem("すみません、英語 gā hanasemasu ka", "Sumimasen, eigo ga hanasemasu ka", "Permisi, apakah bisa berbicara Bahasa Inggris?", "Berguna jika Anda butuh penjelasan detail."),
                VocabularyItem("助けてください", "Tasukete kudasai", "Tolong bantu saya!", "Digunakan saat situasi darurat.")
            )
        ),
        TopicCategory(
            name = "Arah & Navigasi",
            description = "Frasa penting untuk mencari arah, belok kiri-kanan, dan bepergian dengan transportasi umum.",
            iconName = "directions",
            items = listOf(
                VocabularyItem("まっすぐ", "Massugu", "Lurus / Terus lurus", "Berjalan lurus tanpa belok."),
                VocabularyItem("右に曲がる", "Migi ni magaru", "Belok kanan", "Belok ke arah kanan."),
                VocabularyItem("左に曲がる", "Hidari ni magaru", "Belok kiri", "Belok ke arah kiri."),
                VocabularyItem("信号 (しんごう)", "Shingou", "Lampu lalu lintas", "Sangat berguna sebagai patokan penunjuk jalan."),
                VocabularyItem("ここから遠いですか", "Koko kara tooi desu ka", "Apakah jauh dari sini?", "Menanyakan jarak lokasi."),
                VocabularyItem("近くに駅はありますか", "Chikaku ni eki wa arimasu ka", "Apakah ada stasiun di dekat sini?", "Menanyakan stasiun terdekat."),
                VocabularyItem("地図を書いてください", "Chizu wo kaite kudasai", "Tolong gambarkan peta", "Gunakan jika tersesat atau sinyal internet buruk.")
            )
        ),
        TopicCategory(
            name = "Keluarga & Hubungan",
            description = "Panggilan anggota keluarga dan menceritakan hubungan kepada orang lain.",
            iconName = "family",
            items = listOf(
                VocabularyItem("家族 (かぞく)", "Kazoku", "Keluarga", "Kata umum untuk menyebut keluarga."),
                VocabularyItem("父 (ちち)", "Chichi", "Ayah (saya)", "Digunakan saat membicarakan ayah sendiri kepada orang lain."),
                VocabularyItem("母 (はは)", "Haha", "Ibu (saya)", "Digunakan saat membicarakan ibu sendiri kepada orang lain."),
                VocabularyItem("お父さん", "Otousan", "Ayah (orang lain)", "Bentuk sopan untuk memanggil atau menyebut ayah orang lain."),
                VocabularyItem("お母さん", "Okaasan", "Ibu (orang lain)", "Bentuk sopan untuk memanggil atau menyebut ibu orang lain."),
                VocabularyItem("友達 (ともだち)", "Tomodachi", "Teman / Sahabat", "Orang terdekat di luar keluarga."),
                VocabularyItem("兄弟 (きょうだい)", "Kyoudai", "Saudara kandung", "Saudara laki-laki atau perempuan."),
                VocabularyItem("私 (わたし)", "Watashi", "Saya / Aku", "Paling umum dan sopan untuk menyebut diri sendiri.")
            )
        ),
        TopicCategory(
            name = "Perasaan & Kesehatan",
            description = "Ekspresi untuk mengungkapkan perasaan dan kondisi kesehatan Anda.",
            iconName = "health",
            items = listOf(
                VocabularyItem("嬉しいです", "Ureshiidesu", "Senang / Gembira", "Ekspresi kegembiraan."),
                VocabularyItem("悲しいです", "Kanashiidesu", "Sedih", "Keadaan emosi sedih."),
                VocabularyItem("疲れた", "Tsukareta", "Capek / Lelah", "Sering diucapkan setelah beraktivitas panjang."),
                VocabularyItem("熱があります", "Netsu ga arimasu", "Demam / Ada panas", "Menyatakan gejala sakit demam."),
                VocabularyItem("頭が痛い", "Atama ga itai", "Sakit kepala / Pusing", "Sakit di area pusing/kepala."),
                VocabularyItem("病院 (びょういん)", "Byouin", "Rumah sakit", "Tempat berobat ketika sakit."),
                VocabularyItem("薬 (くすり)", "Kusuri", "Obat", "Bahan medis yang diminum untuk menyembuhkan penyakit."),
                VocabularyItem("お大事に", "O-daiji ni", "Semoga cepat sembuh", "Diucapkan kepada orang yang sedang terkena sakit.")
            )
        ),
        TopicCategory(
            name = "Cuaca & Musim",
            description = "Kosakata populer dan ekspresi tentang cuaca serta empat musim di Jepang.",
            iconName = "weather",
            items = listOf(
                VocabularyItem("天気 (てんき)", "Tenki", "Cuaca", "Keadaan alam sehari-hari."),
                VocabularyItem("雨 (あめ)", "Ame", "Hujan", "Sering turun di musim hujan (tsuyu)."),
                VocabularyItem("晴れ (はれ)", "Hare", "Cerah / Terang", "Cuaca bagus dan bersinar."),
                VocabularyItem("雪 (ゆき)", "Yuki", "Salju", "Sangat dinanti oleh turis di musim dingin."),
                VocabularyItem("暑い", "Atsui", "Panas (cuaca)", "Udara terasa terik."),
                VocabularyItem("寒い", "Samui", "Dingin (cuaca)", "Udara dingin menusuk tulang."),
                VocabularyItem("春 (はる)", "Haru", "Musim Semi", "Musim bunga sakura bermekaran."),
                VocabularyItem("夏 (なつ)", "Natsu", "Musim Panas", "Identik dengan festival kembang api (Hanabi) dan festival musim panas."),
                VocabularyItem("秋 (あき)", "Aki", "Musim Gugur", "Musim daun-daun berubah warna menjadi merah (Momiji)."),
                VocabularyItem("冬 (ふゆ)", "Fuyu", "Musim Dingin", "Musim bersalju di bagian utara Jepang.")
            )
        ),
        TopicCategory(
            name = "Belanja & Pasar",
            description = "Kosakata penting saat bertransaksi, menanyakan ketersediaan barang, dan menawar belanjaan.",
            iconName = "shopping",
            items = listOf(
                VocabularyItem("買い物 (かいもの)", "Kaimono", "Belanja", "Aktivitas membeli barang."),
                VocabularyItem("店 (みせ)", "Mise", "Toko", "Tempat menjual barang atau jasa."),
                VocabularyItem("これを見せてください", "Kore wo misete kudasai", "Tolong perlihatkan yang ini", "Sambil menunjuk barang yang dipajang."),
                VocabularyItem("カードは使えますか", "Kaado wa tsakaemasu ka", "Apakah bisa pakai kartu?", "Menanyakan pembayaran nontunai."),
                VocabularyItem("袋は要りません", "Fukuro wa irimasen", "Tidak usah pakai kantong plastik", "Bentuk ramah lingkungan jika membawa tas sendiri."),
                VocabularyItem("試着できますか", "Shichaku dekimasu ka", "Boleh saya coba pakai dulu?", "Meminta izin mencoba pakaian/sepatu."),
                VocabularyItem("安い", "Yasui", "Murah", "Harga barang terjangkau."),
                VocabularyItem("高い", "Takai", "Mahal / Tinggi", "Harga barang di luar anggaran.")
            )
        ),
        TopicCategory(
            name = "Pendidikan & Sekolah",
            description = "Istilah-istilah umum di lingkungan sekolah, perkuliahan, dan ruang kelas.",
            iconName = "school",
            items = listOf(
                VocabularyItem("学校 (がっこう)", "Gakkou", "Sekolah", "Tempat menuntut ilmu secara formal."),
                VocabularyItem("先生 (せんせい)", "Sensei", "Guru / Dosen", "Panggilan hormat untuk pengajar atau profesi terhormat."),
                VocabularyItem("学生 (がくせい)", "Gakusei", "Pelajar / Mahasiswa", "Orang yang sedang belajar di sekolah/kampus."),
                VocabularyItem("教室 (きょうしつ)", "Kyoushitsu", "Ruang kelas", "Ruangan tempat proses belajar mengajar."),
                VocabularyItem("宿題 (しゅくだい)", "Shukudai", "Pekerjaan Rumah (PR)", "Tugas mandiri yang diberikan guru."),
                VocabularyItem("勉強します", "Benkyou shimasu", "Belajar", "Melakukan aktivitas belajar."),
                VocabularyItem("日本語 (にほんご)", "Nihongo", "Bahasa Jepang", "Bahasa resmi negara Jepang."),
                VocabularyItem("辞書 (じしょ)", "Jisho", "Kamus", "Buku panduan kosakata.")
            )
        ),
        TopicCategory(
            name = "Hobi & Rekreasi",
            description = "Kosakata seputar hobi, kesenangan sehari-hari, dan aktivitas rekreasi pengisi waktu luang.",
            iconName = "sports_esports",
            items = listOf(
                VocabularyItem("趣味 (しゅみ)", "Shumi", "Hobi / Kegemaran", "Kata umum untuk menyebut aktivitas disukai."),
                VocabularyItem("写真 (しゃしん)", "Shashin", "Foto / Fotografi", "Sering digabungkan menjadi 'Shashin wo torimasu' (mengambil foto)."),
                VocabularyItem("読書 (どくしょ)", "Dokusho", "Membaca buku", "Aktivitas membaca karya tulis atau buku."),
                VocabularyItem("映画 (えいが)", "Eiga", "Film / Sinema", "Industri perfilman Jepang dikenal dengan keindahan sinematografinya."),
                VocabularyItem("音楽 (おんがく)", "Ongaku", "Musik", "Secara literal berarti 'menikmati suara'."),
                VocabularyItem("旅行 (りょこう)", "Ryokou", "Traveling / Liburan", "Kegiatan bepergian menjelajah tempat baru."),
                VocabularyItem("アニメ", "Anime", "Animasi Jepang", "Karya anime sangat digemari penikmat pop culture seluruh dunia."),
                VocabularyItem("ゲーム", "Geemu", "Game / Permainan elektronik", "Sektor permainan gawai atau konsol."),
                VocabularyItem("絵を描く (えをかく)", "E wo kaku", "Menggambar / Melukis", "Menciptakan karya seni mural atau sketsa."),
                VocabularyItem("カラオケ", "Karaoke", "Karaoke", "Seni bernyanyi populer di box kecil bersama teman.")
            )
        ),
        TopicCategory(
            name = "Hewan & Alam",
            description = "Nama-nama fauna dan keindahan lingkungan alam sekitar dalam Bahasa Jepang.",
            iconName = "nature",
            items = listOf(
                VocabularyItem("動物 (どうぶつ)", "Doubutsu", "Hewan / Binatang", "Secara harfiah bermakna 'makhluk bergerak'."),
                VocabularyItem("犬 (いぬ)", "Inu", "Anjing", "Sifat kesetiaannya diabadikan pada monumen patung Hachiko di Shibuya."),
                VocabularyItem("猫 (ねこ)", "Neko", "Kucing", "Budaya Maneki-Neko (kucing pemanggil keberuntungan) sangat disukai di pertokoan."),
                VocabularyItem("鳥 (とり)", "Tori", "Burung", "Simbol satwa bersayap di udara."),
                VocabularyItem("魚 (さかな)", "Sakana", "Ikan", "Elemen fundamental hidangan pesisir Jepang."),
                VocabularyItem("森 (もり)", "Mori", "Hutan", "Lambang rimbun pepohonan hijau."),
                VocabularyItem("山 (やま)", "Yama", "Gunung", "Gunung legendaris nasional Jepang adalah Gunung Fuji."),
                VocabularyItem("川 (かわ)", "Kawa", "Sungai", "Umumnya memiliki aliran air yang sangat jernih di pedesaan Jepang."),
                VocabularyItem("海 (うみ)", "Umi", "Laut / Samudra", "Wilayah kepulauan Jepang dikelilingi lautan luas."),
                VocabularyItem("花 (はな)", "Hana", "Bunga", "Kegiatan melihat bunga mekar disebut 'Hanami'.")
            )
        ),
        TopicCategory(
            name = "Pekerjaan & Karir",
            description = "Istilah profesi, pekerjaan sehari-hari, dan bidang industri ekonomi.",
            iconName = "work",
            items = listOf(
                VocabularyItem("仕事 (しごと)", "Shigoto", "Pekerjaan / Karir", "Melaksanakan tugas profesi."),
                VocabularyItem("会社員 (かいしゃいん)", "Kaishain", "Karyawan perusahaan", "Pekerja korporasi, dikenal juga dengan istilah Salaryman."),
                VocabularyItem("医者 (いしゃ)", "Isha", "Dokter / Tabib", "Profesional yang mendiagnosis dan memberi pengobatan medis."),
                VocabularyItem("看護師 (かんごし)", "Kangoshi", "Perawat / Suster", "Menjaga dan merawat pasien di rumah sakit."),
                VocabularyItem("警察官 (けいさつかん)", "Keisatsukan", "Polisi", "Menjaga kedamaian lingkungan pemukiman Jepang (Koban)."),
                VocabularyItem("歌手 (かしゅ)", "Kashu", "Penyanyi / Vokalis", "Orang yang bernyanyi profesional."),
                VocabularyItem("エンジニア", "Enjinia", "Insinyur / Engineer", "Insinyur teknologi perangkat keras maupun lunak."),
                VocabularyItem("公務員 (こうむいん)", "Koumuin", "PNS / Pegawai Pemerintah", "Pekerja sektor administratif publik."),
                VocabularyItem("作家 (さっか)", "Sakka", "Penulis / Novelis", "Kreator naskah atau literatur cerita."),
                VocabularyItem("農家 (のうか)", "Nouka", "Petani / Pelaku Agrikultur", "Penghasil produk pertanian unggulan seperti beras premium.")
            )
        ),
        TopicCategory(
            name = "Warna & Desain",
            description = "Kosakata palet warna-warni dan estetika bentuk visual.",
            iconName = "palette",
            items = listOf(
                VocabularyItem("色 (いろ)", "Iro", "Warna", "Unsur estetika rona visual."),
                VocabularyItem("赤 (あか)", "Aka", "Merah", "Warna lingkaran matahari pada bendera kebangsaan Jepang."),
                VocabularyItem("青 (あお)", "Ao", "Biru", "Warna laut yang luas atau langit tak berawan."),
                VocabularyItem("白 (しろ)", "Shiro", "Putih", "Mencerminkan lambang suci dan kepolosan."),
                VocabularyItem("黒 (くろ)", "Kuro", "Hitam", "Kelam atau kegelapan gelap gulita."),
                VocabularyItem("黄色 (きいろ)", "Kiiro", "Kuning", "Seperti warna buah lemon ranum."),
                VocabularyItem("緑 (みどり)", "Midori", "Hijau", "Merupakan simbol alam asri atau warna minuman Matcha."),
                VocabularyItem("茶色 (ちゃいろ)", "Chairo", "Cokelat (warna)", "Mempunyai makna harfiah warna air seduhan teh."),
                VocabularyItem("オレンジ", "Orenji", "Oranye / Jingga", "Serapan asimilasi untuk warna jeruk."),
                VocabularyItem("ピンク", "Pinku", "Merah muda / Pink", "Rona ikonik kelopak sakura di musim semi.")
            )
        ),
        TopicCategory(
            name = "Transportasi Umum",
            description = "Berbagai kendaraan dan infrastruktur perjalanan komuter harian.",
            iconName = "commute",
            items = listOf(
                VocabularyItem("電車 (でんしゃ)", "Densha", "Kereta Listrik", "Urat nadi transportasi utama metropolitan Jepang."),
                VocabularyItem("新幹線 (しんかんせん)", "Shinkansen", "Kereta Peluru (Super cepat)", "Salah satu mahakarya teknologi transportasi tercepat di dunia."),
                VocabularyItem("バス", "Basu", "Bus", "Komuter darat berjadwal ketat."),
                VocabularyItem("飛行機 (ひこうき)", "Hikouki", "Pesawat Terbang", "Digunakan untuk menempuh perjalanan udara lintas negara."),
                VocabularyItem("自転車 (じてんしゃ)", "Jitensha", "Sepeda", "Bersepeda santai melewati jalur pemukiman padat."),
                VocabularyItem("タクシー", "Takushii", "Taksi", "Transportasi luks yang pintunya terbuka otomatis bagi penumpang."),
                VocabularyItem("切符売り場 (きっぷうりば)", "Kippu uriba", "Loket Tiket", "Konsol tempat membeli tiket fisik rute jarak pendek."),
                VocabularyItem("改札口 (かいさつぐち)", "Kaisatsuguchi", "Gerbang Tiket otomatis", "Mesin pemindai kartu transit seperti Suica atau Tarjeta."),
                VocabularyItem("車 (くるま)", "Kuruma", "Mobil", "Secara umum berarti kendaraan beroda."),
                VocabularyItem("地下鉄 (ちかてつ)", "Chikatetsu", "Subway / Kereta Bawah Tanah", "Transportasi bawah tanah penembus Tokyo.")
            )
        ),
        TopicCategory(
            name = "Rumah & Peralatan",
            description = "Istilah bagian rumah tempat tinggal dan sarana perabot rumah tangga.",
            iconName = "home",
            items = listOf(
                VocabularyItem("家 (いえ)", "Ie", "Rumah / Hunian", "Bangunan tempat bermukim keluarga."),
                VocabularyItem("部屋 (へや)", "Heya", "Kamar / Ruangan", "Area privasi di dalam rumah."),
                VocabularyItem("テレビ", "Terebi", "Televisi / TV", "Kata serapan untuk layar kotak hiburan keluarga."),
                VocabularyItem("冷蔵庫 (れいぞうこ)", "Reizouko", "Lemari Es / Kulkas", "Mesin pendingin bahan makanan pokok."),
                VocabularyItem("洗濯機 (せんたくき)", "Sentakuki", "Mesin Cuci", "Alat penampung pembilas baju otomatis."),
                VocabularyItem("電話 (でんわ)", "Denwa", "Telepon", "Saluran suara interkom komunikasi jarak jauh."),
                VocabularyItem("鍵 (かぎ)", "Kagi", "Kunci", "Perangkat penting pembuka pintu utama."),
                VocabularyItem("机 (つくえ)", "Tsukue", "Meja", "Dipakai belajar, berhias, atau membalas surat."),
                VocabularyItem("椅子 (いす)", "Isu", "Kursi / Penyangga duduk", "Tempat mendarat bersantai."),
                VocabularyItem("お風呂 (おふろ)", "O-furo", "Berendam / Bak Mandi", "Berendam bak mandi air panas adalah tradisi relaksasi harian penting di Jepang.")
            )
        )
    )
}
