Reflection - Pemrograman Lanjut

ASSIGMENT 1
Prinsip Clean Code
Prinsip clean code adalah pedoman penting bagi seorang pengembang perangkat lunak agar kode yang ditulis tetap rapi, mudah dipahami, dan sederhana untuk dikelola—terutama ketika bekerja dalam tim. Pada Tutorial 1, saya mempelajari dan mengaplikasikan beberapa prinsip clean code berikut:

- Single Responsibility Principle (SRP): Setiap kelas memiliki satu tanggung jawab spesifik. Sebagai contoh, ProductController hanya menangani permintaan HTTP, ProductService berfokus pada logika bisnis, dan ProductRepository bertugas mengatur akses data.
- Don't Repeat Yourself (DRY): Menghindari duplikasi kode dengan memastikan logika seperti pembuatan, pembaruan, dan penghapusan produk hanya ditulis sekali dan dipanggil sesuai kebutuhan.
- Meaningful Names: Penggunaan nama kelas, metode, dan variabel yang deskriptif dan bermakna, seperti ProductController, createProductPage, dan productData agar lebih jelas fungsinya.
- Separation of Concerns (SoC): Kode dipisahkan berdasarkan lapisan, seperti controller, service, dan repository, untuk memudahkan pemeliharaan dan pengujian.
- Use of Annotations: Anotasi seperti @Controller, @Service, @Repository, dan @Autowired digunakan untuk mengatur dependensi serta konfigurasi Spring secara otomatis.
- Consistent Formatting: Memastikan format kode konsisten, dengan indentasi dan spasi yang rapi sehingga mudah dibaca.

Praktik Kode Aman (Secure Coding Practices)
Selain menerapkan clean code, saya juga memperhatikan keamanan kode demi melindungi produk dari serangan siber dan menjaga data pengguna tetap aman. Beberapa praktik yang saya lakukan meliputi:

- UUID untuk Identitas Produk: Menggunakan UUID sebagai productId agar lebih sulit ditebak dibandingkan ID berupa bilangan integer yang bertambah secara otomatis.
- Perlindungan CSRF: Menambahkan proteksi Cross-Site Request Forgery (CSRF) pada form POST.
- Enkapsulasi Data: Variabel productData di ProductRepository bersifat private, dan data hanya bisa diakses melalui metode khusus yang terenkapsulasi.

Area untuk Peningkatan
Meski sudah menerapkan prinsip-prinsip tersebut, ada beberapa aspek yang masih bisa saya tingkatkan:

- Penanganan Error: Saat ini, jika findById tidak menemukan produk, ia mengembalikan null. Ini bisa menyebabkan NullPointerException. Saya berencana menggunakan Optional atau mengimplementasikan exception handling yang lebih baik.
- Validasi Tambahan: Validasi input baru dilakukan di sisi client (HTML). Saya ingin memperkuatnya dengan validasi di sisi server untuk memastikan, misalnya, productName tidak kosong dan productQuantity selalu lebih dari nol.

Saya merasa Tutorial 1 memberikan wawasan yang solid tentang penerapan prinsip clean code dan praktik keamanan. Selanjutnya, saya akan mendalami topik Continuous Integration (CI) pada Refleksi 2.

Refleksi 2
1. Setelah menerapkan unit testing, saya merasa lebih percaya diri karena bisa memverifikasi bahwa kode yang saya buat berjalan sesuai harapan. Namun, saya menyadari tantangannya, terutama ketika harus menulis tes yang berulang dan terkadang terasa monoton. Tantangan lain adalah menangani edge case ketika menguji fungsi yang kompleks.

- Berapa banyak unit test yang dibutuhkan dalam satu kelas? Unit test idealnya mencakup seluruh logika bisnis dan metode publik, termasuk skenario positif dan negatif serta berbagai kemungkinan parameter.
- Bagaimana memastikan unit test sudah cukup? Menggunakan code coverage sebagai acuan untuk memeriksa sejauh mana kode diuji. Meski demikian, review dari rekan satu tim yang berpengalaman tetap penting untuk memastikan kelengkapan tes.
- Apakah code coverage 100% menjamin program bebas bug? Tidak selalu. Code coverage hanya mengukur sejauh mana kode diuji, tetapi tidak menjamin tidak adanya bug, terutama bug yang muncul akibat kesalahan integrasi antar komponen yang mungkin tidak terdeteksi oleh unit test.

2. Saya juga menyadari bahwa penambahan functional test baru yang sebagian besar mirip dengan CreateProductFunctionalTest.java bisa berdampak buruk pada kualitas kode. Masalah yang muncul antara lain:

- Duplikasi Kode: Jika prosedur setup dan variabel instance identik di beberapa kelas test, ini bisa membuat program menjadi kurang efisien dan membuang memori.
- Sulit Dipelihara: Jika ada perubahan pada variabel instance, kita harus memperbarui beberapa file test sekaligus, misalnya di CreateProductFunctionalTest.java dan test case baru.

Solusinya, saya berencana membuat kelas dasar seperti BaseFunctionalTest.java untuk menampung instance variable dan prosedur setup umum. Kelas seperti CreateProductFunctionalTest.java bisa mewarisi BaseFunctionalTest dan hanya menambah metode spesifik sesuai kebutuhan pengujian. Hal ini juga berlaku untuk test case baru, agar lebih efisien dan minim duplikasi.

Dengan demikian, penerapan unit test dan functional test yang baik bisa menjaga kualitas kode tetap terjaga, sambil memastikan keamanan dan fungsionalitas produk secara menyeluruh.



ASSIGMENT 2
Refleksi

Setelah mengintegrasikan proyek dengan Sonarcloud, terdapat beberapa isu yang terdeteksi. Meski begitu, mayoritas merupakan isu minor yang cukup mudah dan cepat untuk diperbaiki. Dua perbaikan yang saya lakukan meliputi:
Menghapus Import yang Tidak Digunakan
Sejalan dengan prinsip clean code, import yang tidak relevan sebaiknya dihapus agar kode tetap bersih dan terstruktur. Umumnya, import yang saya hilangkan berasal dari kelas pada modul pengujian yang tidak terpakai, sehingga aman untuk dihapus.

Pengelompokkan Dependency
Ini juga berkaitan dengan prinsip clean code. Dengan mengelompokkan dependency berdasarkan fungsinya — seperti untuk program utama, pengembangan, CI/CD, dan pengujian — kode menjadi lebih terorganisir dan mudah ditinjau. Perbaikan ini hanya memerlukan penyesuaian pada satu file, yaitu build.gradle.kts.

Saya yakin implementasi saat ini sudah memenuhi prinsip Continuous Integration dan Continuous Deployment. Berikut penjelasannya:
Continuous Integration:
Saya telah mengatur workflow melalui ci.yml untuk menjalankan proses build menggunakan Gradle setiap kali ada push ke repositori. Selain itu, workflow ini juga mencakup konfigurasi pembuatan laporan JaCoCo, baik secara lokal maupun melalui GitHub Actions. Ditambah lagi, ada file build.yml yang berfungsi untuk melakukan build dan analisis kode menggunakan Sonarcloud, sesuai token proyek yang tersimpan di GitHub Secrets. Dengan demikian, workflow ini sudah merepresentasikan prinsip Continuous Integration.

Continuous Deployment:
Proyek telah dihubungkan dengan Koyeb, memungkinkan proses deployment otomatis setiap kali ada perubahan pada branch master.

Berdasarkan penjelasan di atas, saya rasa workflow yang diterapkan sudah cukup matang untuk mendukung Continuous Integration dan Continuous Deployment.

ASSIGMENT 3
Reflection

1. Saya telah menerapkan prinsip SOLID dalam pengerjaan Assignment 3, dan berikut adalah penjelasannya:

SRP (Single Responsibility Principle): Saya melakukan refactoring dengan memisahkan class CarController dan ProductController yang sebelumnya berada dalam satu file. Awalnya, CarController mewarisi ProductController, meskipun keduanya menangani model yang berbeda, yaitu car dan product. Untuk mematuhi prinsip SRP, saya memisahkan kedua class tersebut agar masing-masing hanya memiliki satu tanggung jawab utama, sehingga tidak ada tumpang tindih fungsi.

OCP (Open/Closed Principle): Saya menghindari pembuatan dua interface yang memiliki fungsi serupa dengan menggabungkan ProductService dan CarService ke dalam satu interface bernama BaseService. BaseService berisi method umum seperti create, update, deleteById, findById, dan findAll. Dengan cara ini, saya bisa menambah fungsionalitas baru di dalam implementasi masing-masing service (CarServiceImpl dan ProductServiceImpl) tanpa mengubah struktur BaseService, menjadikannya terbuka untuk ekstensi namun tertutup untuk modifikasi.

LSP (Liskov Substitution Principle): Interface BaseService yang saya buat dirancang secara minimalis sehingga semua method di dalamnya diimplementasikan sepenuhnya oleh subclass seperti CarServiceImpl dan ProductServiceImpl. Ini memastikan bahwa setiap subclass bisa menggantikan peran BaseService tanpa mengubah perilaku dasarnya, sesuai dengan prinsip LSP.

ISP (Interface Segregation Principle): BaseService sudah dirancang secara ringkas dan fungsional, memastikan bahwa tidak ada method berlebih yang tidak diperlukan oleh implementasi subclass. Setiap method di BaseService memang relevan dan digunakan oleh seluruh subclass, sehingga prinsip ISP tetap terjaga.

DIP (Dependency Inversion Principle): Pada level controller, saya tidak langsung menggunakan implementasi konkret seperti CarServiceImpl atau ProductServiceImpl. Sebaliknya, saya menggunakan abstraksi BaseService<T> di mana T bisa berupa model car atau product. Dengan begitu, modul tingkat tinggi bergantung pada abstraksi, bukan pada detail implementasi, sesuai prinsip DIP.

2. Setelah menerapkan prinsip SOLID, saya merasakan bahwa codebase menjadi lebih terstruktur dan mudah untuk dikelola. Contohnya, penggunaan BaseService membuat saya tidak perlu membuat dua interface berbeda untuk model car dan product. Jika di kemudian hari saya ingin menambah model baru, saya cukup membuat implementasinya tanpa perlu menduplikasi interface.

Selain itu, memisahkan ProductController dan CarController juga meningkatkan keterbacaan kode. Menghilangkan dependensi yang tidak relevan, seperti pewarisan yang tidak diperlukan, membantu mengurangi potensi bug atau masalah yang mungkin muncul.

3. Jika saya tidak menerapkan prinsip SOLID, beberapa kelemahan akan terasa, terutama di modul service. Saya harus membuat interface terpisah untuk setiap model, meskipun memiliki setup method yang serupa, yang tentunya akan memperlambat proses pengembangan.

Selain itu, jika CarController tetap mewarisi ProductController, ada risiko pemanggilan method yang seharusnya hanya berlaku untuk product justru digunakan di car, meskipun keduanya berbeda. Ini akan membuat kode sulit dikelola dan rentan terhadap kesalahan. Oleh karena itu, prinsip SOLID benar-benar membantu menjaga kode saya agar tetap bersih, terstruktur, dan mudah diperbarui.

