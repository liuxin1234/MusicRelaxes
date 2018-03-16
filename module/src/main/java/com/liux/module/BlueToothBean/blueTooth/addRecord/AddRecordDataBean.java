package com.liux.module.BlueToothBean.blueTooth.addRecord;

import java.util.List;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.blueTooth.addRecord
 * 项目日期：2018/1/17
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class AddRecordDataBean {


    /**
     * code : 0
     * data : {"id":3941,"ext":{"findings":"1.心率:94 bpm, 心动偏快.n2.心律变异:268 ms, 正常.n3. 心律变异测试相比上次测量结果偏低,注意休息. n(以上诊断为自动分析结果,请联系医生)n","hr":94,"hrv":{"hf":55430.43068870227,"hfnu":55.05436080153587,"lf":44761.53096149828,"psi":1332.02769341957,"rmssd":378.440667357087,"sdnn":268.8938539698567,"vlf":5807.888585684391},"qrs":[{"RR":59,"flag":1,"offset":6003},{"RR":40,"flag":1,"offset":6043},{"RR":121,"flag":1,"offset":6164},{"RR":93,"flag":1,"offset":6257},{"RR":96,"flag":1,"offset":6353},{"RR":52,"flag":1,"offset":6405},{"RR":69,"flag":1,"offset":6474},{"RR":124,"flag":1,"offset":6598},{"RR":93,"flag":1,"offset":6691},{"RR":70,"flag":1,"offset":6761},{"RR":57,"flag":1,"offset":6818},{"RR":62,"flag":1,"offset":6880},{"RR":96,"flag":1,"offset":6976},{"RR":52,"flag":1,"offset":7028},{"RR":69,"flag":1,"offset":7097},{"RR":59,"flag":1,"offset":7156},{"RR":93,"flag":1,"offset":7249},{"RR":99,"flag":1,"offset":7348},{"RR":59,"flag":1,"offset":7407},{"RR":93,"flag":1,"offset":7500},{"RR":62,"flag":1,"offset":7562},{"RR":146,"flag":1,"offset":7708},{"RR":42,"flag":1,"offset":7750},{"RR":78,"flag":1,"offset":7828},{"RR":72,"flag":1,"offset":7900},{"RR":59,"flag":1,"offset":7959},{"RR":98,"flag":1,"offset":8057},{"RR":60,"flag":1,"offset":8117},{"RR":60,"flag":1,"offset":8177},{"RR":67,"flag":1,"offset":8244},{"RR":57,"flag":1,"offset":8301},{"RR":67,"flag":1,"offset":8368},{"RR":1361,"flag":1,"offset":9729},{"RR":91,"flag":1,"offset":9820},{"RR":64,"flag":1,"offset":9884},{"RR":65,"flag":1,"offset":9949},{"RR":90,"flag":1,"offset":10039},{"RR":155,"flag":1,"offset":10194},{"RR":191,"flag":1,"offset":10385},{"RR":63,"flag":1,"offset":10448},{"RR":152,"flag":1,"offset":10600},{"RR":62,"flag":1,"offset":10662},{"RR":95,"flag":1,"offset":10757},{"RR":2478,"flag":1,"offset":13235},{"RR":63,"flag":1,"offset":13298},{"RR":59,"flag":1,"offset":13357},{"RR":120,"flag":1,"offset":13477},{"RR":93,"flag":1,"offset":13570},{"RR":62,"flag":1,"offset":13632},{"RR":65,"flag":1,"offset":13697},{"RR":62,"flag":1,"offset":13759},{"RR":49,"flag":1,"offset":13808},{"RR":78,"flag":1,"offset":13886},{"RR":124,"flag":1,"offset":14010},{"RR":93,"flag":1,"offset":14103},{"RR":126,"flag":1,"offset":14229},{"RR":186,"flag":1,"offset":14415},{"RR":54,"flag":1,"offset":14469},{"RR":65,"flag":1,"offset":14534},{"RR":123,"flag":1,"offset":14657},{"RR":60,"flag":1,"offset":14717},{"RR":380,"flag":1,"offset":15097},{"RR":51,"flag":1,"offset":15148},{"RR":40,"flag":1,"offset":15188},{"RR":90,"flag":1,"offset":15278},{"RR":65,"flag":1,"offset":15343},{"RR":87,"flag":1,"offset":15430},{"RR ":155,"flag":1,"offset":15585},{"RR":59,"flag":1,"offset":15644},{"RR":223,"flag":1,"offset":15867},{"RR":65,"flag":1,"offset":15932},{"RR":87,"flag":1,"offset":16019},{"RR":54,"flag":1,"offset":16073},{"RR":42,"flag":1,"offset":16115},{"RR":59,"flag":1,"offset":16174},{"RR":96,"flag":1,"offset":16270},{"RR":117,"flag":1,"offset":16387},{"RR":103,"flag":1,"offset":16490},{"RR":90,"flag":1,"offset":16580},{"RR":62,"flag":1,"offset":16642},{"RR":124,"flag":1,"offset":16766},{"RR":59,"flag":1,"offset":16825},{"RR":65,"flag":1,"offset":16890},{"RR":86,"flag":1,"offset":16976},{"RR":110,"flag":1,"offset":17086},{"RR":84,"flag":1,"offset":17170},{"RR":58,"flag":1,"offset":17228},{"RR":251,"flag":1,"offset":17479},{"RR":65,"flag":1,"offset":17544},{"RR":51,"flag":1,"offset":17595},{"RR":73,"flag":1,"offset":17668},{"RR":50,"flag":1,"offset":17718},{"RR":71,"flag":1,"offset":17789},{"RR":62,"flag":1,"offset":17851}],"stop_light":0},"file_report":"http://123.57.212.150:8008/data/42/b2/a5/c4/49/1f/42b2a5c4491f284dda639d876a1addcc_71982.pdf","file_url":"http://123.57.212.150:8008/data/42/b2/a5/c4/49/1f/42b2a5c4491f284dda639d876a1addcc_71982"}
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 3941
         * ext : {"findings":"1.心率:94 bpm, 心动偏快.n2.心律变异:268 ms, 正常.n3. 心律变异测试相比上次测量结果偏低,注意休息. n(以上诊断为自动分析结果,请联系医生)n","hr":94,"hrv":{"hf":55430.43068870227,"hfnu":55.05436080153587,"lf":44761.53096149828,"psi":1332.02769341957,"rmssd":378.440667357087,"sdnn":268.8938539698567,"vlf":5807.888585684391},"qrs":[{"RR":59,"flag":1,"offset":6003},{"RR":40,"flag":1,"offset":6043},{"RR":121,"flag":1,"offset":6164},{"RR":93,"flag":1,"offset":6257},{"RR":96,"flag":1,"offset":6353},{"RR":52,"flag":1,"offset":6405},{"RR":69,"flag":1,"offset":6474},{"RR":124,"flag":1,"offset":6598},{"RR":93,"flag":1,"offset":6691},{"RR":70,"flag":1,"offset":6761},{"RR":57,"flag":1,"offset":6818},{"RR":62,"flag":1,"offset":6880},{"RR":96,"flag":1,"offset":6976},{"RR":52,"flag":1,"offset":7028},{"RR":69,"flag":1,"offset":7097},{"RR":59,"flag":1,"offset":7156},{"RR":93,"flag":1,"offset":7249},{"RR":99,"flag":1,"offset":7348},{"RR":59,"flag":1,"offset":7407},{"RR":93,"flag":1,"offset":7500},{"RR":62,"flag":1,"offset":7562},{"RR":146,"flag":1,"offset":7708},{"RR":42,"flag":1,"offset":7750},{"RR":78,"flag":1,"offset":7828},{"RR":72,"flag":1,"offset":7900},{"RR":59,"flag":1,"offset":7959},{"RR":98,"flag":1,"offset":8057},{"RR":60,"flag":1,"offset":8117},{"RR":60,"flag":1,"offset":8177},{"RR":67,"flag":1,"offset":8244},{"RR":57,"flag":1,"offset":8301},{"RR":67,"flag":1,"offset":8368},{"RR":1361,"flag":1,"offset":9729},{"RR":91,"flag":1,"offset":9820},{"RR":64,"flag":1,"offset":9884},{"RR":65,"flag":1,"offset":9949},{"RR":90,"flag":1,"offset":10039},{"RR":155,"flag":1,"offset":10194},{"RR":191,"flag":1,"offset":10385},{"RR":63,"flag":1,"offset":10448},{"RR":152,"flag":1,"offset":10600},{"RR":62,"flag":1,"offset":10662},{"RR":95,"flag":1,"offset":10757},{"RR":2478,"flag":1,"offset":13235},{"RR":63,"flag":1,"offset":13298},{"RR":59,"flag":1,"offset":13357},{"RR":120,"flag":1,"offset":13477},{"RR":93,"flag":1,"offset":13570},{"RR":62,"flag":1,"offset":13632},{"RR":65,"flag":1,"offset":13697},{"RR":62,"flag":1,"offset":13759},{"RR":49,"flag":1,"offset":13808},{"RR":78,"flag":1,"offset":13886},{"RR":124,"flag":1,"offset":14010},{"RR":93,"flag":1,"offset":14103},{"RR":126,"flag":1,"offset":14229},{"RR":186,"flag":1,"offset":14415},{"RR":54,"flag":1,"offset":14469},{"RR":65,"flag":1,"offset":14534},{"RR":123,"flag":1,"offset":14657},{"RR":60,"flag":1,"offset":14717},{"RR":380,"flag":1,"offset":15097},{"RR":51,"flag":1,"offset":15148},{"RR":40,"flag":1,"offset":15188},{"RR":90,"flag":1,"offset":15278},{"RR":65,"flag":1,"offset":15343},{"RR":87,"flag":1,"offset":15430},{"RR ":155,"flag":1,"offset":15585},{"RR":59,"flag":1,"offset":15644},{"RR":223,"flag":1,"offset":15867},{"RR":65,"flag":1,"offset":15932},{"RR":87,"flag":1,"offset":16019},{"RR":54,"flag":1,"offset":16073},{"RR":42,"flag":1,"offset":16115},{"RR":59,"flag":1,"offset":16174},{"RR":96,"flag":1,"offset":16270},{"RR":117,"flag":1,"offset":16387},{"RR":103,"flag":1,"offset":16490},{"RR":90,"flag":1,"offset":16580},{"RR":62,"flag":1,"offset":16642},{"RR":124,"flag":1,"offset":16766},{"RR":59,"flag":1,"offset":16825},{"RR":65,"flag":1,"offset":16890},{"RR":86,"flag":1,"offset":16976},{"RR":110,"flag":1,"offset":17086},{"RR":84,"flag":1,"offset":17170},{"RR":58,"flag":1,"offset":17228},{"RR":251,"flag":1,"offset":17479},{"RR":65,"flag":1,"offset":17544},{"RR":51,"flag":1,"offset":17595},{"RR":73,"flag":1,"offset":17668},{"RR":50,"flag":1,"offset":17718},{"RR":71,"flag":1,"offset":17789},{"RR":62,"flag":1,"offset":17851}],"stop_light":0}
         * file_report : http://123.57.212.150:8008/data/42/b2/a5/c4/49/1f/42b2a5c4491f284dda639d876a1addcc_71982.pdf
         * file_url : http://123.57.212.150:8008/data/42/b2/a5/c4/49/1f/42b2a5c4491f284dda639d876a1addcc_71982
         */

        private int id;             //记录id
        private ExtBean ext;        //分析数据
        private String file_report; //生成报告地址
        private String file_url;    //文件地址

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        public String getFile_report() {
            return file_report;
        }

        public void setFile_report(String file_report) {
            this.file_report = file_report;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public static class ExtBean {
            /**
             * findings : 1.心率:94 bpm, 心动偏快.n2.心律变异:268 ms, 正常.n3. 心律变异测试相比上次测量结果偏低,注意休息. n(以上诊断为自动分析结果,请联系医生)n
             * hr : 94
             * hrv : {"hf":55430.43068870227,"hfnu":55.05436080153587,"lf":44761.53096149828,"psi":1332.02769341957,"rmssd":378.440667357087,"sdnn":268.8938539698567,"vlf":5807.888585684391}
             * qrs : [{"RR":59,"flag":1,"offset":6003},{"RR":40,"flag":1,"offset":6043},{"RR":121,"flag":1,"offset":6164},{"RR":93,"flag":1,"offset":6257},{"RR":96,"flag":1,"offset":6353},{"RR":52,"flag":1,"offset":6405},{"RR":69,"flag":1,"offset":6474},{"RR":124,"flag":1,"offset":6598},{"RR":93,"flag":1,"offset":6691},{"RR":70,"flag":1,"offset":6761},{"RR":57,"flag":1,"offset":6818},{"RR":62,"flag":1,"offset":6880},{"RR":96,"flag":1,"offset":6976},{"RR":52,"flag":1,"offset":7028},{"RR":69,"flag":1,"offset":7097},{"RR":59,"flag":1,"offset":7156},{"RR":93,"flag":1,"offset":7249},{"RR":99,"flag":1,"offset":7348},{"RR":59,"flag":1,"offset":7407},{"RR":93,"flag":1,"offset":7500},{"RR":62,"flag":1,"offset":7562},{"RR":146,"flag":1,"offset":7708},{"RR":42,"flag":1,"offset":7750},{"RR":78,"flag":1,"offset":7828},{"RR":72,"flag":1,"offset":7900},{"RR":59,"flag":1,"offset":7959},{"RR":98,"flag":1,"offset":8057},{"RR":60,"flag":1,"offset":8117},{"RR":60,"flag":1,"offset":8177},{"RR":67,"flag":1,"offset":8244},{"RR":57,"flag":1,"offset":8301},{"RR":67,"flag":1,"offset":8368},{"RR":1361,"flag":1,"offset":9729},{"RR":91,"flag":1,"offset":9820},{"RR":64,"flag":1,"offset":9884},{"RR":65,"flag":1,"offset":9949},{"RR":90,"flag":1,"offset":10039},{"RR":155,"flag":1,"offset":10194},{"RR":191,"flag":1,"offset":10385},{"RR":63,"flag":1,"offset":10448},{"RR":152,"flag":1,"offset":10600},{"RR":62,"flag":1,"offset":10662},{"RR":95,"flag":1,"offset":10757},{"RR":2478,"flag":1,"offset":13235},{"RR":63,"flag":1,"offset":13298},{"RR":59,"flag":1,"offset":13357},{"RR":120,"flag":1,"offset":13477},{"RR":93,"flag":1,"offset":13570},{"RR":62,"flag":1,"offset":13632},{"RR":65,"flag":1,"offset":13697},{"RR":62,"flag":1,"offset":13759},{"RR":49,"flag":1,"offset":13808},{"RR":78,"flag":1,"offset":13886},{"RR":124,"flag":1,"offset":14010},{"RR":93,"flag":1,"offset":14103},{"RR":126,"flag":1,"offset":14229},{"RR":186,"flag":1,"offset":14415},{"RR":54,"flag":1,"offset":14469},{"RR":65,"flag":1,"offset":14534},{"RR":123,"flag":1,"offset":14657},{"RR":60,"flag":1,"offset":14717},{"RR":380,"flag":1,"offset":15097},{"RR":51,"flag":1,"offset":15148},{"RR":40,"flag":1,"offset":15188},{"RR":90,"flag":1,"offset":15278},{"RR":65,"flag":1,"offset":15343},{"RR":87,"flag":1,"offset":15430},{"RR ":155,"flag":1,"offset":15585},{"RR":59,"flag":1,"offset":15644},{"RR":223,"flag":1,"offset":15867},{"RR":65,"flag":1,"offset":15932},{"RR":87,"flag":1,"offset":16019},{"RR":54,"flag":1,"offset":16073},{"RR":42,"flag":1,"offset":16115},{"RR":59,"flag":1,"offset":16174},{"RR":96,"flag":1,"offset":16270},{"RR":117,"flag":1,"offset":16387},{"RR":103,"flag":1,"offset":16490},{"RR":90,"flag":1,"offset":16580},{"RR":62,"flag":1,"offset":16642},{"RR":124,"flag":1,"offset":16766},{"RR":59,"flag":1,"offset":16825},{"RR":65,"flag":1,"offset":16890},{"RR":86,"flag":1,"offset":16976},{"RR":110,"flag":1,"offset":17086},{"RR":84,"flag":1,"offset":17170},{"RR":58,"flag":1,"offset":17228},{"RR":251,"flag":1,"offset":17479},{"RR":65,"flag":1,"offset":17544},{"RR":51,"flag":1,"offset":17595},{"RR":73,"flag":1,"offset":17668},{"RR":50,"flag":1,"offset":17718},{"RR":71,"flag":1,"offset":17789},{"RR":62,"flag":1,"offset":17851}]
             * stop_light : 0
             */

            private String findings;
            private int hr;
            private HrvBean hrv;
            private int stop_light;
            private List<QrsBean> qrs;

            public String getFindings() {
                return findings;
            }

            public void setFindings(String findings) {
                this.findings = findings;
            }

            public int getHr() {
                return hr;
            }

            public void setHr(int hr) {
                this.hr = hr;
            }

            public HrvBean getHrv() {
                return hrv;
            }

            public void setHrv(HrvBean hrv) {
                this.hrv = hrv;
            }

            public int getStop_light() {
                return stop_light;
            }

            public void setStop_light(int stop_light) {
                this.stop_light = stop_light;
            }

            public List<QrsBean> getQrs() {
                return qrs;
            }

            public void setQrs(List<QrsBean> qrs) {
                this.qrs = qrs;
            }

            public static class HrvBean {
                /**
                 * hf : 55430.43068870227
                 * hfnu : 55.05436080153587
                 * lf : 44761.53096149828
                 * psi : 1332.02769341957
                 * rmssd : 378.440667357087
                 * sdnn : 268.8938539698567
                 * vlf : 5807.888585684391
                 */

                private double hf;
                private double hfnu;
                private double lf;
                private double psi;
                private double rmssd;
                private double sdnn;
                private double vlf;

                public double getHf() {
                    return hf;
                }

                public void setHf(double hf) {
                    this.hf = hf;
                }

                public double getHfnu() {
                    return hfnu;
                }

                public void setHfnu(double hfnu) {
                    this.hfnu = hfnu;
                }

                public double getLf() {
                    return lf;
                }

                public void setLf(double lf) {
                    this.lf = lf;
                }

                public double getPsi() {
                    return psi;
                }

                public void setPsi(double psi) {
                    this.psi = psi;
                }

                public double getRmssd() {
                    return rmssd;
                }

                public void setRmssd(double rmssd) {
                    this.rmssd = rmssd;
                }

                public double getSdnn() {
                    return sdnn;
                }

                public void setSdnn(double sdnn) {
                    this.sdnn = sdnn;
                }

                public double getVlf() {
                    return vlf;
                }

                public void setVlf(double vlf) {
                    this.vlf = vlf;
                }
            }

            public static class QrsBean {
                /**
                 * RR : 59
                 * flag : 1
                 * offset : 6003
                 * RR  : 155
                 */

                private int RR;
                private int flag;
                private int offset;


                public int getRR() {
                    return RR;
                }

                public void setRR(int RR) {
                    this.RR = RR;
                }

                public int getFlag() {
                    return flag;
                }

                public void setFlag(int flag) {
                    this.flag = flag;
                }

                public int getOffset() {
                    return offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }


            }
        }
    }
}
