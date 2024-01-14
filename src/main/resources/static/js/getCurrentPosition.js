var options = {
  enableHighAccuracy: false,
  timeout: 5000,
  maximumAge: 0,
};
var lat1="";
var har1="";
var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5666805, 126.9784147),
    zoom: 10,
    mapTypeId: naver.maps.MapTypeId.NORMAL
});

var infowindow = new naver.maps.InfoWindow();

function success(pos) {
  var crd = pos.coords;
  lat1=crd.latitude;
  har1=crd.longitude;
  console.log(lat1+"현재 위도");
  console.log("Your current position is:");
  console.log(`Latitude : ${crd.latitude}`);
  console.log(`Longitude: ${crd.longitude}`);
  console.log(`More or less ${crd.accuracy} meters.`);
//  update(crd.latitude,crd.longitude);




}

function error(err) {
  console.warn(`ERROR(${err.code}): ${err.message}`);
}

navigator.geolocation.getCurrentPosition(success, error, options);

function onSuccessGeolocation(position) {
    var location = new naver.maps.LatLng(position.coords.latitude,
                                         position.coords.longitude);

     navigator.geolocation.getCurrentPosition(success, error, options);

    getNewNotifications(lat1, har1);
     console.log(lat1+"현재 위도");
     function getNewNotifications(lat1, har1) {
               $.ajax({
                 url: '/getNearFood',
                 type: 'GET',
                  data: {
                       lat: lat1,
                       har: har1
                     },
                 success: function(data) {
                   // Process the data received from the server and add it to the page
    //               update(data);
                   


                    update(data);
                 }
               });
             }



    map.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
    map.setZoom(12); // 지도의 줌 레벨을 변경합니다.

    infowindow.setContent('<div style="padding:20px;">' + 'geolocation.getCurrentPosition() 위치' + '</div>');

    infowindow.open(map, location);
    console.log('Coordinates: ' + location.toString());
}

function onErrorGeolocation() {
    var center = map.getCenter();

    infowindow.setContent('<div style="padding:20px;">' +
        '<h5 style="margin-bottom:5px;color:#f00;">Geolocation failed!</h5>'+ "latitude: "+ center.lat() +"<br />longitude: "+ center.lng() +'</div>');

    infowindow.open(map, center);
}

$(window).on("load", function() {
    if (navigator.geolocation) {
        /**
         * navigator.geolocation 은 Chrome 50 버젼 이후로 HTTP 환경에서 사용이 Deprecate 되어 HTTPS 환경에서만 사용 가능 합니다.
         * http://localhost 에서는 사용이 가능하며, 테스트 목적으로, Chrome 의 바로가기를 만들어서 아래와 같이 설정하면 접속은 가능합니다.
         * chrome.exe --unsafely-treat-insecure-origin-as-secure="http://example.com"
         */






        navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
    } else {
        var center = map.getCenter();
        infowindow.setContent('<div style="padding:20px;"><h5 style="margin-bottom:5px;color:#f00;">Geolocation not supported</h5></div>');
        infowindow.open(map, center);
    }
});
