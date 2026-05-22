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
        )
    )
}
