var map;

function initMap() {
  var daelim = { lat: 37.40358356615854 ,lng: 126.93035052651962 };
  map = new google.maps.Map( document.getElementById('map'), {
      zoom: 12,
      center: daelim
    });

  new google.maps.Marker({
    position: daelim,
    map: map,
    label: "대림대"
  });
}