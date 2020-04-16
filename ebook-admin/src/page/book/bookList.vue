<template>
  <div>
    <div class="book_list">
      <el-table
        :border="true"
        :data="bookList"
        stripe
        style="width: 100%; height: 100%;"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column fixed prop="bookId" label="ID"></el-table-column>
        <el-table-column prop="fileName" label="文件名"></el-table-column>
        <el-table-column prop="title" label="书名"></el-table-column>
        <el-table-column prop="Introduction" label="简介"></el-table-column>
        <el-table-column prop="language" label="语言"></el-table-column>
        <el-table-column prop="publisher" label="出版社"></el-table-column>
        <el-table-column prop="creator" label="作者"></el-table-column>
        <el-table-column prop="isbn" label="ISBN"></el-table-column>
        <el-table-column prop="publishDate" label="出版日期"></el-table-column>
        <el-table-column fixed="right" label="状态" width="100">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small"
              >查看</el-button
            >
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">
      <el-pagination
        background
        layout="prev,pager, next, jumper, sizes"
        :total="bookTotal"
        :page-size="page.size"
        :page-sizes="[10, 20, 30, 40]"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getBookInfoList, getBookTotal } from "@/api";

export default {
  data() {
    return {
      bookList: [
        {
          bookId: "",
          fileName: "",
          title: "",
          Introduction: "",
          language: "",
          publisher: "",
          creator: "",
          isbn: "",
          publishDate: "",
        },
      ],
      page: {
        size: 10,
        currentPage: 1,
      },
      bookTotal: 0,
    };
  },
  mounted() {
    this.getBookInfoList(this.page.size, 0);
    getBookTotal().then((Response) => {
      if (Response.data.code == 200) {
        this.bookTotal = Response.data.result;
      }
    });
  },
  methods: {
    getBookInfoList(num, offset) {
      getBookInfoList(num, offset).then((Response) => {
        if (Response.data.code == 200) {
          this.bookList = Response.data.result;
        }
      });
    },
    handleCurrentChange(cPage) {
      this.page.currentPage = cPage;
      this.getBookInfoList(this.page.size, this.page.size * (cPage - 1));
    },
    handleSizeChange(pageSize) {
      this.getBookInfoList(
        pageSize,
        this.page.size * (this.page.currentPage - 1)
      );
      this.page.size = pageSize;
    },
  },
};
</script>
<style lang="less" scoped>
.book_list {
  margin-bottom: 58px;
}
.pagination {
  height: 48px;
  padding-top: 24px;
  width: 100%;
  position: absolute;
  bottom: 0;
  background-color: #eff2f7;
  z-index: 999;
}
</style>
