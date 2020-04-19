<template>
  <div class="book_info">
    <h1>电子书详情</h1>
    <el-form ref="form" :model="bookForm" label-width="80px">
      <el-form-item label="ID">
        <el-input v-model="bookForm.bookId" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="文件名">
        <el-input v-model="bookForm.fileName" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="书名">
        <el-input v-model="bookForm.title"></el-input>
      </el-form-item>
      <el-form-item label="简介">
        <el-input type="textarea" placeholder="限制500字" v-model="bookForm.Introduction"></el-input>
      </el-form-item>
      <el-form-item label="语言">
        <el-input v-model="bookForm.language"></el-input>
      </el-form-item>
      <el-form-item label="出版社">
        <el-input v-model="bookForm.publisher"></el-input>
      </el-form-item>
      <el-form-item label="作者">
        <el-input v-model="bookForm.creator"></el-input>
      </el-form-item>
      <el-form-item label="ISBN">
        <el-input v-model="bookForm.isbn"></el-input>
      </el-form-item>
      <el-form-item label="出版日期">
        <el-date-picker
          v-model="bookForm.publishDate"
          :value-format="'yyyy-MM-dd HH:mm:ss'"
          type="datetime"
          placeholder="选择日期时间"
        ></el-date-picker>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  props: {
    bookForm: Object
  }
};
</script>
<style lang="less" scoped>
.book_info {
  width: 550px;
  h1 {
    text-align: center;
    margin-bottom: 16px;
  }
}
</style>