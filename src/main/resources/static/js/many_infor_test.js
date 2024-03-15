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
                          data[i].name// Assuming content holds y coordinate



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
                zIndex: 100
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

        if (infoWindow.getMap()) {
        infoWindow.close();
        } else {
        infoWindow.open(map, marker);
//        callApi("/menu",'get',(id,2);
        console.log("정보창 열림 ")

        }
        }
    }

    for (var i=0, ii=markers.length; i<ii; i++) {
    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
    }

}