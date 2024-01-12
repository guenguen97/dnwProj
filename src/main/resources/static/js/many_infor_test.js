

function update() {
var HOME_PATH = window.HOME_PATH || '.';
  var ulElement = document.getElementById('myList');

    // Get the last li tag among the child tags of ul
    var lastLiTag = ulElement.lastElementChild;

    // Get the first child tag (div in this case) of the last li tag
    var firstChildText = lastLiTag.firstElementChild.firstElementChild.textContent;


var MARKER_SPRITE_X_OFFSET = 29,
MARKER_SPRITE_Y_OFFSET = 50,
MARKER_SPRITE_POSITION = {};

for(var i = 1; i <=firstChildText; i++){
  var classSelector = '.' + (i + 1); // Generating class selector based on the value of i
 var element = $('.'+i);
 if (element) {
         var secondChild = element.children('span').eq(1); // Second child
         var thirdChild = element.children('span').eq(2); // Third child

         if (secondChild && thirdChild) {

             var latText = secondChild.text();
             var harText = thirdChild.text();
             }
   }

MARKER_SPRITE_POSITION[i] =[
                                         parseFloat(latText), // Assuming content holds x coordinate
                                         parseFloat(harText) // Assuming content holds y coordinate
                                     ];
}




var map = new naver.maps.Map('map', {
center: new naver.maps.LatLng(37.3595704, 127.105399),
zoom: 13
});

var bounds = map.getBounds(),
southWest = bounds.getSW(),
northEast = bounds.getNE(),
lngSpan = northEast.lng() - southWest.lng(),
latSpan = northEast.lat() - southWest.lat();

var markers = [],
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
                content: '<div style="width:150px;text-align:center;padding:10px;">The Letter is <b>"'+ key.substr(0, 1) +'"</b>.</div>'
                });

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
}
}
}

for (var i=0, ii=markers.length; i<ii; i++) {
naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
}

}