package com.liux.module.BlueToothBean.music;

import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.music
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class MusicListDto implements Serializable{

    /**
     * Data : {"Rows":[{"Id":"string","MusicCategoryId":"string","MusicCategoryName":"string","Name":"string","FileUrl":"string","FileId":"string","BurningTime":"string","Formart":"string","Size":"string","LFHFMin":0,"LFHFMax":0,"ClickNum":0,"SortIndex":0,"Author":"string","PublicationYear":"string","Description":"string","CreateTime":"2018-01-11T08:05:57.528Z"}],"PageIndex":0,"PageSize":0,"TotalCount":0,"TotalPages":0,"StartPosition":0,"EndPosition":0,"HasPreviousPage":true,"HasNextPage":true}
     * Success : true
     * Message : string
     * Info : {}
     */

    private DataBean Data;
    private boolean Success;
    private String Message;
    private InfoBean Info;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class DataBean implements Serializable{
        /**
         * Rows : [{"Id":"string","MusicCategoryId":"string","MusicCategoryName":"string","Name":"string","FileUrl":"string","FileId":"string","BurningTime":"string","Formart":"string","Size":"string","LFHFMin":0,"LFHFMax":0,"ClickNum":0,"SortIndex":0,"Author":"string","PublicationYear":"string","Description":"string","CreateTime":"2018-01-11T08:05:57.528Z"}]
         * PageIndex : 0
         * PageSize : 0
         * TotalCount : 0
         * TotalPages : 0
         * StartPosition : 0
         * EndPosition : 0
         * HasPreviousPage : true
         * HasNextPage : true
         */

        private int PageIndex;
        private int PageSize;
        private int TotalCount;
        private int TotalPages;
        private int StartPosition;
        private int EndPosition;
        private boolean HasPreviousPage;
        private boolean HasNextPage;
        private List<RowsBean> Rows;

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getTotalPages() {
            return TotalPages;
        }

        public void setTotalPages(int TotalPages) {
            this.TotalPages = TotalPages;
        }

        public int getStartPosition() {
            return StartPosition;
        }

        public void setStartPosition(int StartPosition) {
            this.StartPosition = StartPosition;
        }

        public int getEndPosition() {
            return EndPosition;
        }

        public void setEndPosition(int EndPosition) {
            this.EndPosition = EndPosition;
        }

        public boolean isHasPreviousPage() {
            return HasPreviousPage;
        }

        public void setHasPreviousPage(boolean HasPreviousPage) {
            this.HasPreviousPage = HasPreviousPage;
        }

        public boolean isHasNextPage() {
            return HasNextPage;
        }

        public void setHasNextPage(boolean HasNextPage) {
            this.HasNextPage = HasNextPage;
        }

        public List<RowsBean> getRows() {
            return Rows;
        }

        public void setRows(List<RowsBean> Rows) {
            this.Rows = Rows;
        }

        public static class RowsBean implements Serializable{
            /**
             * Id : string
             * MusicCategoryId : string
             * MusicCategoryName : string
             * Name : string
             * FileUrl : string
             * FileId : string
             * BurningTime : string
             * Formart : string
             * Size : string
             * LFHFMin : 0
             * LFHFMax : 0
             * ClickNum : 0
             * SortIndex : 0
             * Author : string
             * PublicationYear : string
             * Description : string
             * CreateTime : 2018-01-11T08:05:57.528Z
             */

            private String Id;
            private String MusicCategoryId;
            private String MusicCategoryName;
            private String Name;
            private String FileUrl;
            private String FileId;
            private String BurningTime;
            private String Formart;
            private String Size;
            private int LFHFMin;
            private int LFHFMax;
            private int ClickNum;
            private int SortIndex;
            private String Author;
            private String PublicationYear;
            private String Description;
            private String CreateTime;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getMusicCategoryId() {
                return MusicCategoryId;
            }

            public void setMusicCategoryId(String MusicCategoryId) {
                this.MusicCategoryId = MusicCategoryId;
            }

            public String getMusicCategoryName() {
                return MusicCategoryName;
            }

            public void setMusicCategoryName(String MusicCategoryName) {
                this.MusicCategoryName = MusicCategoryName;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getFileUrl() {
                return FileUrl;
            }

            public void setFileUrl(String FileUrl) {
                this.FileUrl = FileUrl;
            }

            public String getFileId() {
                return FileId;
            }

            public void setFileId(String FileId) {
                this.FileId = FileId;
            }

            public String getBurningTime() {
                return BurningTime;
            }

            public void setBurningTime(String BurningTime) {
                this.BurningTime = BurningTime;
            }

            public String getFormart() {
                return Formart;
            }

            public void setFormart(String Formart) {
                this.Formart = Formart;
            }

            public String getSize() {
                return Size;
            }

            public void setSize(String Size) {
                this.Size = Size;
            }

            public int getLFHFMin() {
                return LFHFMin;
            }

            public void setLFHFMin(int LFHFMin) {
                this.LFHFMin = LFHFMin;
            }

            public int getLFHFMax() {
                return LFHFMax;
            }

            public void setLFHFMax(int LFHFMax) {
                this.LFHFMax = LFHFMax;
            }

            public int getClickNum() {
                return ClickNum;
            }

            public void setClickNum(int ClickNum) {
                this.ClickNum = ClickNum;
            }

            public int getSortIndex() {
                return SortIndex;
            }

            public void setSortIndex(int SortIndex) {
                this.SortIndex = SortIndex;
            }

            public String getAuthor() {
                return Author;
            }

            public void setAuthor(String Author) {
                this.Author = Author;
            }

            public String getPublicationYear() {
                return PublicationYear;
            }

            public void setPublicationYear(String PublicationYear) {
                this.PublicationYear = PublicationYear;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }
        }
    }

    public static class InfoBean {
    }
}
