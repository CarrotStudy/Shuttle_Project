//var map;

//function initMap() {
//  var daelim = { lat: 37.40358356615854 ,lng: 126.93035052651962 };
//  map = new google.maps.Map( document.getElementById('map'), {
//      zoom: 12,
//      center: daelim
//    });
//
//  new google.maps.Marker({
//    position: daelim,
//    map: map,
//    label: "대림대"
//  });
//}
//
//        function initMap() {
//            const map = new google.maps.Map(document.getElementById("map"), {
//                zoom: 14,
//                center: { lat: 37.5407622, lng: 127.0706095 },
//            });
//            for (var i = 0; i < locations.length; i++) {
//                var marker = new google.maps.Marker({
//                    map: map,
//                    label: locations[i].place,
//                    position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
//                });
//            }
//        }
//        const locations = [
//            { place:"건대입구역", lat: 37.539922, lng: 127.070609 },
//            { place:"어린이대공원역", lat: 37.547263, lng: 127.074181 },
//        ];

function initMap() {
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 8,
    center: { lat: -34.397, lng: 150.644 },
  });
  const geocoder = new google.maps.Geocoder();
  var address = [];
  var data = /*[[ ${address} ]]*/
  console.log(data)
//  address[0] = "대한민국 대구광역시 북구 산격1동 북구대산초등학교";
//  address[1] = "대한민국 경기도 수원시 장안구 조원2동 송원로 장안구청";
  for (var i = 0; i < address.length; i++) {
    geocoder
    .geocode({ address: address[i] })
    . then(({ results }) =>{
        map.setCenter(results[0].geometry.location);
        new google.maps.Marker({
            map: map,
            position: results[0].geometry.location,
        });
    })
    .catch((e) =>
        alert("Geocode was not successful for the following reason: " + e)
    );
  }
}

