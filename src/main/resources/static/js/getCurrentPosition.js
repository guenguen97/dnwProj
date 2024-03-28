
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
                    for(var i=1 ; i<data.length;i++ ){
                        console.log("db 에 나온거"+data[i].name);
                    }

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

    infowindow.setContent('<div style="padding:20px;">' + '현재 위치' + '</div>');

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


var markers = [];

function update(data) {
// console.log(myLat+" and "+myHar);
var HOME_PATH = window.HOME_PATH || '.';
  var ulElement = document.getElementById('myList');

  console.log("데이타의 길이는"+data.length);

  for (var i = 0; i < markers.length; i++) {
     console.log("기존 마커 삭제");
        var marker ;
        marker = markers[i]
        position = marker.getPosition();


            hideMarker(map, marker);

        }
        //초기화 해준 마커 다시 선언
         markers = [];


    // Get the last li tag among the child tags of ul
    var lastLiTag = ulElement.lastElementChild;



var MARKER_SPRITE_X_OFFSET = 29,
MARKER_SPRITE_Y_OFFSET = 50,
MARKER_SPRITE_POSITION = {};
    const dataArray = [];


// 위도랑 경도 랑 음식점 이름 넣어줄 for 문
                for(var i=1 ; i<data.length;i++ ){

                   console.log(data[i].latitude);
                   console.log(data[i].name);

               MARKER_SPRITE_POSITION[i] =[
//                        parseFloat(data[i][8]), // Assuming content holds x coordinate
//                        parseFloat(data[i][9]),
//                        data[i][1]// Assuming content holds y coordinate

                        data[i].latitude, // Assuming content holds x coordinate
                         data[i].longitude,
                          data[i].name,// Assuming content holds y coordinate
                        data[i].id //음식점 고유 db id


                   ];
                }


infoWindows = [];

for (var key in MARKER_SPRITE_POSITION) {
                var position = new naver.maps.LatLng(

//                위도 경도 눌 부분
                MARKER_SPRITE_POSITION[key][0],
                MARKER_SPRITE_POSITION[key][1]);
//                console.log(MARKER_SPRITE_POSITION[key][0]);
                var marker = new naver.maps.Marker({

                map: map,
                position: position,
                title: key,
                icon: {
                url: HOME_PATH +'/img/example/sp_pins_spot_v3.png',
                size: new naver.maps.Size(24, 37),
                anchor: new naver.maps.Point(12, 37),
                origin: new naver.maps.Point(MARKER_SPRITE_POSITION[key][0], MARKER_SPRITE_POSITION[key][1])
                },
                zIndex: 100,
                id :  MARKER_SPRITE_POSITION[key][3],
                storeName :  MARKER_SPRITE_POSITION[key][2]

                });

                var infoWindow = new naver.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:10px;"><b>"'+  MARKER_SPRITE_POSITION[key][2] +'"</b>.</div>'
                });


//보이는 영역 만 표시할떄 쓰는건데 당장 필요없음
                markers.push(marker);
                infoWindows.push(infoWindow);
    };

    naver.maps.Event.addListener(map, 'idle', function() {
    updateMarkers(map, markers);
    });

    function updateMarkers(map, markers) {

        var mapBounds = map.getBounds();
        var marker, position;



        for (var i = 0; i < markers.length; i++) {

        marker = markers[i]
        position = marker.getPosition();

            if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
            } else {
            hideMarker(map, marker);
            }
        }
    }

    function showMarker(map, marker) {

    if (marker.setMap()) return;
    marker.setMap(map);
    }

    function hideMarker(map, marker) {

        if (!marker.setMap()) return;
        marker.setMap(null);
    }

    // 해당 마커의 인덱스를 seq라는 클로저 변수로 저장하는 이벤트 핸들러를 반환합니다.
    function getClickHandler(seq) {
        return function(e) {
        var marker = markers[seq],
        infoWindow = infoWindows[seq];
        console.log(marker);
        console.log(infoWindow);

        if (infoWindow.getMap()) {
        infoWindow.close();
        } else {
                infoWindow.open(map, marker);
                 function displayComments(list) {
                                         const commentsContainer = document.getElementById('menuContainer');
                                         commentsContainer.innerHTML = ''; //클릭될떄마다 메뉴는 초기화
                                        commentsContainer.innerHTML += `
                                            <div class="top-container" style="display:flex;">
                                                <div class="storeName" style="margin-right:40px;">
                                                    ${marker.storeName}
                                                </div>
                                                <div class="likeButton" onclick="likeOrNot(${marker.id});">
                                                    저장
                                                </div>
                                            </div>

                                        `
                                             list.forEach(item => {
                                                   commentsContainer.innerHTML += `
                                                       <div class="comment">
                                                           <div> ${item}</div>
                                                       </div>
                                                   `;
                                               });

                  }

                displayComments(callApi("/menu/"+marker.id,'get',""));


                        console.log("정보창 열림8 ")

        }
        }
    }

    for (var i=0, ii=markers.length; i<ii; i++) {
    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
    }

}
//음식점 db id 기억하기 위해
let storeID ;

function likeOrNot(id){

    let test=callApi("/test",'get',null);
    console.log(test.message+"!!!!!!!!!!!!!!")

       let islogined =callApi("/user/logined",'get',"");
       console.log("로그인 유무의 결과는 "+islogined.message);

      if(islogined.message =="not login"){
            alert("로그인 이후 저장이 가능합니다");
            return;
        }

    else{
         let countEachGroupStore =getJson("/likeStore/count/groupName/"+id,"")
         console.log(countEachGroupStore.맛집);
         countEachGroupStoreFunction(countEachGroupStore);

    layerPop('likePopup');
    }
    storeID = id;



}

function countEachGroupStoreFunction(countEachGroupStore){
      var $layer = $("#likePopup");

                if(countEachGroupStore.맛집==1){
                      console.log("맛집에 이미 있음")
                      $layer.find("#option1").next("label").css('border',"1px solid red");
                }
                if(countEachGroupStore["가보고 싶은 곳"]==1){
                          $layer.find("#option2").next("label").css('border',"1px solid red");
                    }
                if(countEachGroupStore.기타==1){
                              $layer.find("#option3").next("label").css('border',"1px solid red");
                }
}