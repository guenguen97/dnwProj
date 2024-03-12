
var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5666805, 126.9784147),
    zoom: 10,
    mapTypeId: naver.maps.MapTypeId.NORMAL
});
firstMyLocation();

var infowindow = new naver.maps.InfoWindow();


//좌표 기준으로 주위 음식점 정보 호출 api
  function getNewNotifications(lat, har) {
               $.ajax({
                 url: '/getNearFood',
                 type: 'GET',
                  data: {
                       lat: lat,
                       har: har
                     },
                 success: function(data) {
                   // Process the data received from the server and add it to the page
    //               update(data);

                    console.log(data);
                   //many_infor_test 에 있는 함수 주변 마커 뜨게하기
                    update(data);


                 }
               });
             }

navigator.geolocation.getCurrentPosition(success, error, options);

function onSuccessGeolocation(position) {



    getNewNotifications(lat1, har1);
     console.log(lat1+"현재 위도"+har1);



    var location = new naver.maps.LatLng(position.coords.latitude,
                                         position.coords.longitude);

     navigator.geolocation.getCurrentPosition(success, error, options);

    map.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
    map.setZoom(15); // 지도의 줌 레벨을 변경합니다.

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

function firstMyLocation() {
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
}





// 현재 위치로 이동하는 함수
function goCurrentPosition(){

       navigator.geolocation.getCurrentPosition(success, error, options);
        var location = new naver.maps.LatLng(lat1,
                                             har1);

        map.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
        map.setZoom(15);


}


//지도 상 중심의 좌표를 구하면서 주위 음식점 호출
function getFoodNearMap(){
    var centerLat=map.getCenter().y;
    var centerHar=map.getCenter().x;
    console.log(centerHar+"!!!!!!!!!!!!");
    getNewNotifications(centerLat, centerHar);


}



    function initGeocoder() {
    var latlng = map.getCenter();
    var utmk = naver.maps.TransCoord.fromLatLngToUTMK(latlng); // 위/경도 -> UTMK
    var tm128 = naver.maps.TransCoord.fromUTMKToTM128(utmk);   // UTMK -> TM128
    var naverCoord = naver.maps.TransCoord.fromTM128ToNaver(tm128); // TM128 -> NAVER

    infoWindow = new naver.maps.InfoWindow({
        content: ''
    });

    map.addListener('click', function(e) {
        var latlng = e.coord,
            utmk = naver.maps.TransCoord.fromLatLngToUTMK(latlng),
            tm128 = naver.maps.TransCoord.fromUTMKToTM128(utmk),
            naverCoord = naver.maps.TransCoord.fromTM128ToNaver(tm128);

        utmk.x = parseFloat(utmk.x.toFixed(1));
        utmk.y = parseFloat(utmk.y.toFixed(1));

        infoWindow.setContent([
            '<div style="padding:10px;width:380px;font-size:14px;line-height:20px;">',
            '<strong>LatLng</strong> : '+ '좌 클릭 지점 위/경도 좌표' +'<br />',
            '<strong>UTMK</strong> : '+ '위/경도 좌표를 UTMK 좌표로 변환한 값' +'<br />',
            '<strong>TM128</strong> : '+ '변환된 UTMK 좌표를 TM128 좌표로 변환한 값' +'<br />',
            '<strong>NAVER</strong> : '+ '변환된 TM128 좌표를 NAVER 좌표로 변환한 값' +'<br />',
            '</div>'
        ].join(''));

        infoWindow.open(map, latlng);
        console.log('LatLng: ' + latlng.toString());
        console.log('UTMK: ' + utmk.toString());
        console.log('TM128: ' + tm128.toString());
        console.log('NAVER: ' + naverCoord.toString());
    });
}

naver.maps.onJSContentLoaded = initGeocoder;
