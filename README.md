# Patika+ Cohort Order Management System Console App
# Ev Projesi

Bu proje, Java Spring Boot kullanılarak geliştirilmiştir ve farklı tiplerdeki evleri (Ev, Villa, Yazlık) temsil eden sınıfları ve bu evler üzerinde çeşitli işlemler gerçekleştiren bir servis sınıfını içerir. Proje ayrıca Swagger kullanılarak API dökümantasyonu sağlanmıştır.

## Proje Yapısı

- **Ev:** Temel ev sınıfıdır. Metrekare, odaların ve salonun sayısı gibi özellikleri içerir.
- **Villa:** Ev sınıfından türetilmiş bir alt sınıftır. Ayrıca havuz, bahçe gibi villa özelliklerini içerir.
- **Yazlık:** Ev sınıfından türetilmiş bir alt sınıftır. Genellikle tatil amaçlı kullanılan evlerdir.

## Örnekler ve Metotlar

### Evlerin Listesi

- `getEvList()`: Tüm evleri içeren bir liste döndürür.
- `getVillaList()`: Villaları içeren bir liste döndürür.
- `getYazlikList()`: Yazlık evleri içeren bir liste döndürür.

### Fiyat Hesaplamaları

- `evlerinToplamFiyati()`: Tüm evlerin toplam fiyatını döndürür.
- `villalarinToplamFiyati()`: Villaların toplam fiyatını döndürür.
- `yazliklarinToplamFiyati()`: Yazlıkların toplam fiyatını döndürür.
- `tumEvlerinToplamFiyati()`: Tüm ev tiplerinin toplam fiyatını döndürür.

### Metrekare Ortalamaları

- `evlerinOrtalamaMetrekare()`: Tüm evlerin ortalama metrekare değerini döndürür.
- `villalarinOrtalamaMetrekare()`: Villaların ortalama metrekare değerini döndürür.
- `yazliklarinOrtalamaMetrekare()`: Yazlıkların ortalama metrekare değerini döndürür.
- `tumEvlerinOrtalamaMetrekare()`: Tüm ev tiplerinin ortalama metrekare değerini döndürür.

### Filtreleme

- `odaVeSalonaGoreEvFiltrele(int odaSayisi, int salonSayisi)`: Verilen oda ve salon sayısına göre evleri filtreler ve bir liste olarak döndürür.

## Kullanım

Projenin kullanımı için öncelikle gerekli sınıfların oluşturulması ve örneklerin oluşturulması gerekmektedir. Daha sonra bu örnekler üzerinde ilgili metotlar çağrılarak işlemler gerçekleştirilebilir.

Örneğin:

```java
// Örnek Evlerin Oluşturulması
Ev ev1 = new Ev(100, 3, 1);
Villa villa1 = new Villa(250, 5, 3, true, true);
Yazlik yazlik1 = new Yazlik(150, 4, 2, false);

// Servis İşlemleri
System.out.println(evService.evlerinToplamFiyati()); // Tüm evlerin toplam fiyatını yazdırır.
System.out.println(evService.villalarinOrtalamaMetrekare()); // Villaların ortalama metrekare değerini yazdırır.
System.out.println(evService.odaVeSalonaGoreEvFiltrele(4, 2)); // 4 oda ve 2 salon sayısına sahip evleri filtreler.
```

## Swagger Dökümantasyonu

Bu proje, Swagger kullanılarak API dökümantasyonu sağlamaktadır. Swagger arayüzüne erişmek için projenizi çalıştırdıktan sonra tarayıcınızdan şu URL'yi ziyaret edebilirsiniz:

```
http://localhost:8080/swagger-ui.html
```


