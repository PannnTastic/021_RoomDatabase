package com.example.roomdatabase_021.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdatabase_021.R
import com.example.roomdatabase_021.room.Siswa
import com.example.roomdatabase_021.view.route.DestinasiHome
import com.example.roomdatabase_021.viewmodel.HomeViewModel
import com.example.roomdatabase_021.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    Scaffold(
        modifier = modifier,
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.insert)
                )
            }
        }
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homUiState.collectAsState()
        BodyHome(
            itemSiswa = uiStateSiswa.listSiswa,
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        )

    }
}

@Composable
fun BodyHome(
    itemSiswa: List<Siswa>,
    modifier : Modifier = Modifier
){

        if (itemSiswa.isEmpty()){
            Text(stringResource(R.string.noitem),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
            )
        }
        else{
            ListSiswa(
                itemSiswa = itemSiswa,
                modifier = modifier.padding(horizontal = 8.dp)
            )
        }

}

@Composable
fun ListSiswa(
    itemSiswa: List<Siswa>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(items = itemSiswa, key = {it.id}){
                person -> DataSiswa(
            siswa = person,
            modifier = Modifier
                .padding(all = 8.dp)
        )
        }

    }
}

@Composable
fun DataSiswa(
    siswa: Siswa,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)))
        {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(siswa.nama,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(siswa.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(siswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }

    }
}