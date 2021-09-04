function initMap() {
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 8,
    center: { lat: -34.397, lng: 150.644 },
  });
  const geocoder = new google.maps.Geocoder();
  var address = [];
  address[0] = "대한민국 대구광역시 북구 산격1동 북구대산초등학교";
  address[1] = "대한민국 경기도 수원시 장안구 조원2동 송원로 장안구청";
  address[2] = "군포시 산본동 문화예술회관사거리";
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

