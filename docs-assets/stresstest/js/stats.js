var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2205",
        "ok": "2205",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "11218",
        "ok": "11218",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "6600",
        "ok": "6600",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2154",
        "ok": "2154",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6459",
        "ok": "6459",
        "ko": "-"
    },
    "percentiles2": {
        "total": "7954",
        "ok": "7954",
        "ko": "-"
    },
    "percentiles3": {
        "total": "10320",
        "ok": "10320",
        "ko": "-"
    },
    "percentiles4": {
        "total": "11092",
        "ok": "11092",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 200,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "13.333",
        "ok": "13.333",
        "ko": "-"
    }
},
contents: {
"req_post-campaign-b4445": {
        type: "REQUEST",
        name: "Post Campaign",
path: "Post Campaign",
pathFormatted: "req_post-campaign-b4445",
stats: {
    "name": "Post Campaign",
    "numberOfRequests": {
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "4610",
        "ok": "4610",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "11218",
        "ok": "11218",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "7722",
        "ok": "7722",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1868",
        "ok": "1868",
        "ko": "-"
    },
    "percentiles1": {
        "total": "7764",
        "ok": "7764",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9317",
        "ok": "9317",
        "ko": "-"
    },
    "percentiles3": {
        "total": "10615",
        "ok": "10615",
        "ko": "-"
    },
    "percentiles4": {
        "total": "11095",
        "ok": "11095",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 100,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "6.667",
        "ok": "6.667",
        "ko": "-"
    }
}
    },"req_get-all-campaig-0ab98": {
        type: "REQUEST",
        name: "Get all campaigns in 1 second",
path: "Get all campaigns in 1 second",
pathFormatted: "req_get-all-campaig-0ab98",
stats: {
    "name": "Get all campaigns in 1 second",
    "numberOfRequests": {
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2205",
        "ok": "2205",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8413",
        "ok": "8413",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "5479",
        "ok": "5479",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1809",
        "ok": "1809",
        "ko": "-"
    },
    "percentiles1": {
        "total": "5610",
        "ok": "5610",
        "ko": "-"
    },
    "percentiles2": {
        "total": "7048",
        "ok": "7048",
        "ko": "-"
    },
    "percentiles3": {
        "total": "8257",
        "ok": "8257",
        "ko": "-"
    },
    "percentiles4": {
        "total": "8392",
        "ok": "8392",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 0,
        "percentage": 0
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 100,
        "percentage": 100
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "6.667",
        "ok": "6.667",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
